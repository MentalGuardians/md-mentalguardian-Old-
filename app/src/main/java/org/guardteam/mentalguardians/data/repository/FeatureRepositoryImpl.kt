package org.guardteam.mentalguardians.data.repository

import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.guardteam.mentalguardians.utils.Result
import org.guardteam.mentalguardians.data.mapper.toContent
import org.guardteam.mentalguardians.data.mapper.toContentById
import org.guardteam.mentalguardians.data.mapper.toHistoryData
import org.guardteam.mentalguardians.data.mapper.toPrediction
import org.guardteam.mentalguardians.data.mapper.toResponse
import org.guardteam.mentalguardians.data.mapper.toTherapist
import org.guardteam.mentalguardians.data.mapper.toTherapistById
import org.guardteam.mentalguardians.data.mapper.toTransaction
import org.guardteam.mentalguardians.data.remote.ApiService
import org.guardteam.mentalguardians.domain.manager.LocalDataManager
import org.guardteam.mentalguardians.domain.model.HistoryData
import org.guardteam.mentalguardians.data.mapper.toProfileData
import org.guardteam.mentalguardians.domain.model.Content
import org.guardteam.mentalguardians.domain.model.ContentById
import org.guardteam.mentalguardians.domain.model.Prediction
import org.guardteam.mentalguardians.domain.model.Response
import org.guardteam.mentalguardians.domain.model.Therapist
import org.guardteam.mentalguardians.domain.model.TherapistById
import org.guardteam.mentalguardians.domain.model.Transaction
import org.guardteam.mentalguardians.domain.repository.FeatureRepository
import org.guardteam.mentalguardians.domain.model.Profile
import retrofit2.HttpException

class FeatureRepositoryImpl(
    private val apiService: ApiService,
    private val localDataManager: LocalDataManager
) : FeatureRepository {

    override fun predict(text: String): Flow<Result<Prediction>> = flow {
        emit(Result.Loading)
        try {
            val userId = runBlocking { localDataManager.getUserData().first().userId }
            val response = apiService.predict(
                userId = userId,
                text = text
            )

            emit(Result.Success(response.toPrediction()))
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

    override fun history(): Flow<Result<HistoryData>> = flow {
        emit(Result.Loading)
        try {
            val userId = runBlocking { localDataManager.getUserData().first().userId }
            val response = apiService.historyPredict(userId = userId)
            emit(Result.Success(response.toHistoryData()))
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

    override fun content(content: String): Flow<Result<Content>> = flow {
        emit(Result.Loading)
        try {
            val response = apiService.contentRecommender(content)

            emit(Result.Success(response.toContent()))
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

    override fun contentById(contentId: String): Flow<Result<ContentById>> = flow {
        emit(Result.Loading)
        try {
            val response = apiService.contentById(contentId)

            emit(Result.Success(response.toContentById()))
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

    override fun expert(expert: String): Flow<Result<Therapist>> = flow {
        emit(Result.Loading)
        try {
            val response = apiService.expertRecommender(expert)

            emit(Result.Success(response.toTherapist()))
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

    override fun expertById(therapistId: String): Flow<Result<TherapistById>> = flow {
        emit(Result.Loading)
        try {
            val response = apiService.expertById(therapistId)

            emit(Result.Success(response.toTherapistById()))
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

    override fun booking(
        therapistId: String,
        date: String,
        time: String,
        method: String
    ): Flow<Result<Response>> = flow {
        emit(Result.Loading)
        try {
            val userId = runBlocking { localDataManager.getUserData().first().userId }
            val response = apiService.booking(userId, therapistId, date, time, method)

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

    override fun profile(): Flow<Result<Profile>> = flow {
        emit(Result.Loading)
        try {
            val userId = runBlocking { localDataManager.getUserData().first().userId }
            val response = apiService.profile(userId = userId)

            emit(Result.Success(response.toProfileData()))
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

    override fun transaction(): Flow<Result<Transaction>> = flow {
        emit(Result.Loading)
        try {
            val userId = runBlocking { localDataManager.getUserData().first().userId }
            val response = apiService.transaction(userId)

            emit(Result.Success(response.toTransaction()))
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


    override fun editProfile(
        username: String,
        password: String,
        email: String,
        phone: String,
        alamat: String
    ): Flow<Result<Response>> = flow {
        emit(Result.Loading)
        try {
            val userId = runBlocking { localDataManager.getUserData().first().userId }
            val response = apiService.editProfile(userId, username, password, email,phone, alamat)

            emit(Result.Success(response.toResponse()))
        } catch (e: Exception){
            if (e is HttpException){
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, Response::class.java)
                 emit(Result.Error(errorBody.message))
            } else {
              emit(Result.Error(e.message.toString()))
            }
        }
    }

    override fun cancelBooking(bookingId: String): Flow<Result<Response>> = flow {
        emit(Result.Loading)
        try {
            val response = apiService.cancelBooking(bookingId)

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

}



