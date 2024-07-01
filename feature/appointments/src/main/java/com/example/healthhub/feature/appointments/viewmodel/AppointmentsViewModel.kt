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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AppointmentsViewModel(
    private val appointmentsRepository: AppointmentsRepository,
    private val getUsersInfoUseCase: GetUsersInfoUseCase
) : ViewModel() {

    var uiState by mutableStateOf(AppointmentsUiState())
        private set

    init {
        loadAppointments()
    }

    fun retry() {
        loadAppointments()
    }

    private fun loadAppointments() {
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
}
