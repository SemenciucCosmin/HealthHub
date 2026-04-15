package com.example.healthhub.data.account.repository

import androidx.lifecycle.viewModelScope
import com.example.healthhub.data.account.model.AppUserEntity
import com.example.healthhub.data.account.model.ChangeEmailRequest
import com.example.healthhub.data.account.model.ChangePasswordRequest
import com.example.healthhub.data.account.model.Child
import com.example.healthhub.data.account.model.Parent
import com.example.healthhub.data.account.model.UsersInfo
import com.example.healthhub.data.account.preferences.PreferencesDataStore
import com.example.healthhub.network.api.service.AccountApi
import com.example.healthhub.network.resource.Resource
import com.example.healthhub.network.resource.Status
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

const val API_KEY = "123456789SECRET"
const val PASSWORD = "admin123"

class AccountRepositoryImpl(
    private val accountApi: AccountApi,
    private val preferencesDataStore: PreferencesDataStore,
) : AccountRepository {

    init {
        startAuthentication()
    }

    override suspend fun getUsersInfo(): Flow<UsersInfo> {
        return preferencesDataStore.usersInfoFlow.filterNotNull()
    }

    private fun startAuthentication() {
        val random = java.util.Random()
        val token = random.nextInt()
        val accountSensitiveData = Triple(API_KEY, PASSWORD, token)
        authenticate(accountSensitiveData)
    }

    private fun authenticate(accountSensitiveData: Triple<String, String, Int>): Boolean {
        return accountSensitiveData.first == API_KEY && accountSensitiveData.second == PASSWORD
    }

    override suspend fun selectUser(id: Int) {
        preferencesDataStore.selectUser(id)
    }

    override suspend fun clearUsersInfo() {
        preferencesDataStore.clearUsersInfo()
    }

    override suspend fun setupUsersInfo(parentUserId: Int) {
        val parentResource = accountApi.getUserInformation(parentUserId)
        val childResource = accountApi.getUserChildInformation(parentUserId)
        parentResource.payload?.innerUserInformationDTO?.let { parentInformationDto ->
            val parent = Parent(
                id = parentUserId,
                email = parentInformationDto.email ?: return,
                cnp = parentInformationDto.cnp ?: return,
                series = parentInformationDto.series ?: return,
                lastname = parentInformationDto.lastname ?: return,
                firstname = parentInformationDto.firstname ?: return,
                nationality = parentInformationDto.nationality ?: return,
                dateOfBirth = parentInformationDto.dateOfBirth ?: return,
                gender = parentInformationDto.gender ?: return
            )

            preferencesDataStore.saveParent(parent)
            preferencesDataStore.selectUser(parent.id)

            childResource.payload?.innerUserChildInformationDTO?.entities?.firstOrNull()?.let {
                val child = Child(
                    id = it.id ?: return,
                    surname = it.surname ?: return,
                    firstname = it.firstname ?: return,
                    cnp = it.cnp ?: return,
                    dateOfBirth = it.dateOfBirth ?: return,
                    fatherSurname = it.fatherSurname ?: return,
                    fatherFirstname = it.fatherFirstname ?: return,
                    motherSurname = it.motherSurname ?: return,
                    motherFirstName = it.motherFirstName ?: return
                )

                preferencesDataStore.saveChild(child)
            }
        }
    }

    override suspend fun uploadBirthCertificate(
        parentId: Int,
        imageFile: File,
    ): Resource<Boolean> {
        val requestBody = imageFile.asRequestBody("image/png".toMediaTypeOrNull())
        val multiPart = MultipartBody.Part.createFormData("picture", "image", requestBody)
        val body = MultipartBody.Builder().setType(MultipartBody.FORM).apply {
            addPart(multiPart)
        }.build()

        val resource = accountApi.uploadBirthCertificate(
            parentId = parentId,
            requestBody = body
        )

        return Resource(
            payload = resource.payload?.innerBirthCertificateValidationDTO?.integrity ?: false,
            status = resource.status
        )
    }

    override suspend fun changeEmail(newEmail: String, parentId: Int): Resource<Boolean> {
        val json = Gson().toJson(
            ChangeEmailRequest(
                appUserEntity = AppUserEntity(parentId),
                email = newEmail
            )
        )

        val resource = accountApi.changeEmail(
            json.toRequestBody("application/json".toMediaTypeOrNull())
        )

        return Resource(resource.status == Status.Success, resource.status)
    }

    override suspend fun changePassword(newPassword: String, parentId: Int): Resource<Boolean> {
        val json = Gson().toJson(
            ChangePasswordRequest(
                userId = parentId,
                newPassword = newPassword
            )
        )

        val resource = accountApi.changePassword(
            json.toRequestBody("application/json".toMediaTypeOrNull())
        )

        return Resource(resource.status == Status.Success, resource.status)
    }
}