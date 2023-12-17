package org.guardteam.mentalguardians.data.repository

import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.data.mapper.toLogin
import org.guardteam.mentalguardians.data.mapper.toResponse
import org.guardteam.mentalguardians.data.remote.ApiService
import org.guardteam.mentalguardians.domain.model.Login
import org.guardteam.mentalguardians.domain.model.Response
import org.guardteam.mentalguardians.domain.repository.AuthRepository
import retrofit2.HttpException

class AuthRepositoryImpl(
    private val apiService: ApiService
) : AuthRepository {
    override fun register(name: String, email: String, password: String): Flow<Result<Response>> =
        flow {
            emit(Result.Loading)
            try {
                val response = apiService.register(
                    username = name,
                    email = email,
                    password = password
                )

                emit(Result.Success(response.toResponse()))
            } catch (e: Exception) {
                if (e is HttpException) {
                    val jsonInString = e.response()?.errorBody()?.string()
                    val errorBody = Gson().fromJson(jsonInString, Response::class.java)
                    emit(Result.Error(errorBody.message))
                } else {
                    emit(Result.Error(e.message.toString()))
                }
            }
        }

    override fun login(email: String, password: String): Flow<Result<Login>> = flow {
        emit(Result.Loading)
        try {
            val response = apiService.login(
                email = email,
                password = password
            )

            emit(Result.Success(response.toLogin()))
        } catch (e: Exception) {
            if (e is HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, Response::class.java)
                emit(Result.Error(errorBody.message))
            } else {
                emit(Result.Error(e.message.toString()))
            }
        }
    }
}