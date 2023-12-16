package org.guardteam.mentalguardians.data.repository

import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.guardteam.mentalguardians.common.utils.Result
import org.guardteam.mentalguardians.data.mapper.toHistoryData
import org.guardteam.mentalguardians.data.mapper.toPrediction
import org.guardteam.mentalguardians.data.remote.ApiService
import org.guardteam.mentalguardians.domain.manager.LocalDataManager
import org.guardteam.mentalguardians.domain.model.HistoryData
import org.guardteam.mentalguardians.data.mapper.toContent
import org.guardteam.mentalguardians.data.mapper.toContentById
import org.guardteam.mentalguardians.domain.model.Content
import org.guardteam.mentalguardians.domain.model.ContentById
import org.guardteam.mentalguardians.domain.model.Prediction
import org.guardteam.mentalguardians.domain.model.Response
import org.guardteam.mentalguardians.domain.repository.FeatureRepository
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

    override fun history(historyId: String): Flow<Result<HistoryData>> = flow {
        emit(Result.Loading)
        try {
            val response = apiService.historyPredict(historyId)
            emit(Result.Success(response.toHistoryData()))
        } catch (e: Exception) {
            if (e is HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, Response::class.java)
                emit(Result.Error(errorBody.message))
            }else {
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
}

