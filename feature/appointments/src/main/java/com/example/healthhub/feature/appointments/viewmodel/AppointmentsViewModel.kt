package com.example.healthhub.feature.appointments.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthhub.data.appointments.model.AppointmentTimeframe
import com.example.healthhub.data.appointments.repository.AppointmentsRepository
import com.example.healthhub.domain.account.GetUsersInfoUseCase
import com.example.healthhub.feature.appointments.viewmodel.model.AppointmentsUiState
import com.example.healthhub.network.resource.Status
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AppointmentsViewModel(
    private val appointmentsRepository: AppointmentsRepository,
    private val getUsersInfoUseCase: GetUsersInfoUseCase
) : ViewModel() {

    var uiState by mutableStateOf(AppointmentsUiState())
        private set

    fun loadAppointments() {
        viewModelScope.launch {
            uiState = AppointmentsUiState()

            getUsersInfoUseCase().collectLatest { usersInfo ->
                val pastAppointmentsResource = appointmentsRepository.getAppointments(
                    usersInfo = usersInfo,
                    timeframe = AppointmentTimeframe.PAST
                )

                val futureAppointmentsResource = appointmentsRepository.getAppointments(
                    usersInfo = usersInfo,
                    timeframe = AppointmentTimeframe.FUTURE
                )

                val isPastAppointmentsError = pastAppointmentsResource.status != Status.Success
                val isFutureAppointmentsError = futureAppointmentsResource.status != Status.Success
                val pastAppointments = pastAppointmentsResource.payload ?: emptyList()
                val futureAppointments = futureAppointmentsResource.payload ?: emptyList()

                when {
                    isPastAppointmentsError || isFutureAppointmentsError -> {
                        uiState = uiState.copy(
                            isLoading = false,
                            isError = true
                        )
                    }

                    else -> {
                        uiState = uiState.copy(
                            pastAppointments = pastAppointments,
                            futureAppointments = futureAppointments,
                            isLoading = false,
                            isError = false
                        )
                    }
                }
            }
        }
    }

    fun loadDataForAppointmentCreation() {
        viewModelScope.launch {
            uiState = uiState.copy(
                isLoading = true,
                isError = false,
            )

            val specializationsAsync = async { appointmentsRepository.getSpecializations() }
            val countiesAsync = async { appointmentsRepository.getCounties() }

            val specializationsResource = specializationsAsync.await()
            val countiesResource = countiesAsync.await()

            val specializations = specializationsResource.payload
            val counties = countiesResource.payload

            uiState = when {
                specializations.isNullOrEmpty() || counties.isNullOrEmpty() -> uiState.copy(
                    isLoading = false,
                    isError = true
                )

                else -> uiState.copy(
                    specializations = specializations,
                    counties = counties,
                    isLoading = false
                )
            }
        }
    }

    fun loadPresetsByMedicId(medicId: Int) {
        viewModelScope.launch {
            appointmentsRepository.getMedics().payload?.firstOrNull {
                it.id == medicId
            }?.let { medic ->
                uiState = uiState.copy(
                    specializations = medic.specializations,
                    counties = listOf(medic.county),
                    isLoading = false
                )
            }
        }
    }

    fun filterAppointments(
        specializationName: String,
        countyName: String,
        startDateMillis: Long
    ) {
        viewModelScope.launch {
            val resource = appointmentsRepository.filterAppointments(
                specializationName = specializationName,
                countyName = countyName,
                startDateMillis = startDateMillis
            )

            when {
                resource.status != Status.Success -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        isError = true
                    )
                }

                else -> {
                    uiState = uiState.copy(
                        filteredAppointments = resource.payload ?: emptyList(),
                        isLoading = false,
                        isError = false
                    )
                }
            }
        }
    }
}
