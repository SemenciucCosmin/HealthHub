package com.example.healthhub.feature.medicalfile.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthhub.data.appointments.model.AppointmentTimeframe
import com.example.healthhub.data.appointments.repository.AppointmentsRepository
import com.example.healthhub.data.medicalfile.repository.MedicalFileRepository
import com.example.healthhub.domain.account.GetUsersInfoUseCase
import com.example.healthhub.feature.medicalfile.viewmodel.model.MedicalFileUiState
import com.example.healthhub.network.resource.Status
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MedicalFileViewModel(
    private val medicalFileRepository: MedicalFileRepository,
    private val appointmentsRepository: AppointmentsRepository,
    private val getUsersInfoUseCase: GetUsersInfoUseCase
) : ViewModel() {

    var uiState by mutableStateOf(MedicalFileUiState())
        private set

    init {
        loadMedics()
        loadPastAppointments()
    }

    private fun loadMedics() {
        viewModelScope.launch {
            uiState = uiState.copy(
                isLoading = true,
                isError = false,
            )

            val resource = medicalFileRepository.getMedics()

            uiState = when (val medics = resource.payload) {
                null -> uiState.copy(
                    isLoading = false,
                    isError = true
                )

                else -> uiState.copy(
                    medics = medics,
                    isLoading = false
                )
            }
        }
    }

    private fun loadPastAppointments() {
        viewModelScope.launch {
            getUsersInfoUseCase().collectLatest { usersInfo ->
                uiState = uiState.copy(
                    isLoading = true,
                    isError = false,
                )

                val resource = appointmentsRepository.getAppointments(
                    usersInfo = usersInfo,
                    timeframe = AppointmentTimeframe.PAST
                )

                val appointments = resource.payload ?: emptyList()

                uiState = when (resource.status) {
                    Status.Success -> uiState.copy(
                        pastAppointments = appointments,
                        isLoading = false,
                        isError = false
                    )

                    else -> uiState.copy(
                        isLoading = false,
                        isError = true
                    )
                }
            }
        }
    }

    fun retry() = loadMedics()
}
