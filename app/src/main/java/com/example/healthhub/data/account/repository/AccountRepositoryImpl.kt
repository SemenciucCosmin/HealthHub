package com.example.healthhub.data.account.repository

import com.example.healthhub.data.account.model.User
import com.example.healthhub.data.preferences.repository.PreferencesRepository
import com.example.healthhub.network.api.service.AccountApi
import kotlinx.coroutines.flow.Flow

class AccountRepositoryImpl(
    private val accountApi: AccountApi,
    private val preferencesRepository: PreferencesRepository,
) : AccountRepository {
    override suspend fun getUserInformation(): Flow<User?> {
        return preferencesRepository.getUserInformation()
    }

    override suspend fun setUserInformation(userId: Int) {
        val resource = accountApi.getUserInformation(userId)
        resource.payload?.innerUserInformationDTO?.let { userInformationDto ->
            val user = User(
                id = userId,
                email = userInformationDto.email,
                cnp = userInformationDto.cnp,
                series = userInformationDto.series,
                lastname = userInformationDto.lastname,
                firstname = userInformationDto.firstname,
                nationality = userInformationDto.nationality,
                dateOfBirth = userInformationDto.dateOfBirth,
                sex = userInformationDto.sex,
            )

            preferencesRepository.saveUserInformation(user)
        }
    }
}