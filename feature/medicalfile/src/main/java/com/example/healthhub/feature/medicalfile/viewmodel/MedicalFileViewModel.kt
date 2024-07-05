package com.example.healthhub.feature.medicalfile.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthhub.data.appointments.model.AppointmentTimeframe
import com.example.healthhub.data.appointments.repository.AppointmentsRepository
import com.example.healthhub.domain.account.GetUsersInfoUseCase
import com.example.healthhub.feature.medicalfile.viewmodel.model.MedicalFileUiState
import com.example.healthhub.network.resource.Status
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class MedicalFileViewModel(
    private val appointmentsRepository: AppointmentsRepository,
    private val getUsersInfoUseCase: GetUsersInfoUseCase
) : ViewModel() {

    var uiState by mutableStateOf(MedicalFileUiState())
        private set

    init {
        loadPastAppointments()
        loadMedics()
        loadSpecializations()
    }

    private fun loadMedics() {
        viewModelScope.launch {
            val usersInfo = getUsersInfoUseCase().firstOrNull() ?: return@launch

            uiState = uiState.copy(
                selectedSpecializationId = null,
                isLoading = true,
                isError = false,
            )

            val medicsAsync = async { appointmentsRepository.getMedics() }
            val medicsByIdAsync = async { appointmentsRepository.getMedicsById(usersInfo) }

            val medics= medicsAsync.await().payload
            val medicsById = medicsByIdAsync.await().payload

            uiState = when {
                medics == null || medicsById == null -> uiState.copy(
                    isLoading = false,
                    isError = true
                )

                else -> uiState.copy(
                    medics = medics,
                    medicsById = medicsById,
                    filteredMedics = medics,
                    filteredMedicsById = medicsById,
                    isLoading = false
                )
            }
        }
    }

    private fun loadSpecializations() {
        viewModelScope.launch {
            uiState = uiState.copy(
                isLoading = true,
                isError = false,
            )

            val resource = appointmentsRepository.getSpecializations()

            uiState = when (val specializations = resource.payload) {
                null -> uiState.copy(
                    isLoading = false,
                    isError = true
                )

                else -> uiState.copy(
                    specializations = specializations,
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

    fun retry() {
        loadMedics()
        loadSpecializations()
    }

    fun selectSpecialization(specializationId: Int?) {
        val filteredMedics = uiState.medics.filter { medic ->
            medic.specializations.map { it.id }.contains(specializationId)
        }

        val filteredMedicsById = uiState.medicsById.filter { medic ->
            medic.specializations.map { it.id }.contains(specializationId)
        }

        uiState = uiState.copy(
            selectedSpecializationId = specializationId,
            filteredMedics = if (filteredMedics.isEmpty() && specializationId == null) {
                uiState.medics
            } else {
                filteredMedics
            },
            filteredMedicsById = if (filteredMedicsById.isEmpty() && specializationId == null) {
                uiState.medicsById
            } else {
                filteredMedicsById
            }
        )
    }
}
