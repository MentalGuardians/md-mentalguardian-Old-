package org.guardteam.mentalguardians.data.remote

import org.guardteam.mentalguardians.data.remote.dto.HistoryDataItem
import org.guardteam.mentalguardians.data.remote.dto.HistoryDto
import org.guardteam.mentalguardians.data.remote.dto.ContentByIdDto
import org.guardteam.mentalguardians.data.remote.dto.ContentDto
import org.guardteam.mentalguardians.data.remote.dto.LoginDto
import org.guardteam.mentalguardians.data.remote.dto.PredictionDto
import org.guardteam.mentalguardians.data.remote.dto.ResponseDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): ResponseDto

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginDto

    @FormUrlEncoded
    @POST("predict")
    suspend fun predict(
        @Field("userId") userId: String,
        @Field("text") text: String
    ): PredictionDto 

    @FormUrlEncoded
    @POST("content-recommender")
    suspend fun contentRecommender(
        @Field("content") content: String
    ): ContentDto


    @FormUrlEncoded
    @GET("predict")
    suspend fun historyPredict(
        @Query("userId") userId: String
    ): HistoryDto

    @GET("content-recommender")
    suspend fun contentById(
        @Query("contentId") contentId: String
    ): ContentByIdDto
}