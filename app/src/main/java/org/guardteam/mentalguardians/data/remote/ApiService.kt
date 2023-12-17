package org.guardteam.mentalguardians.data.remote

import org.guardteam.mentalguardians.data.remote.dto.ContentByIdDto
import org.guardteam.mentalguardians.data.remote.dto.ContentDto
import org.guardteam.mentalguardians.data.remote.dto.HistoryDto
import org.guardteam.mentalguardians.data.remote.dto.LoginDto
import org.guardteam.mentalguardians.data.remote.dto.PredictionDto
import org.guardteam.mentalguardians.data.remote.dto.ProfileDto
import org.guardteam.mentalguardians.data.remote.dto.ResponseDto
import org.guardteam.mentalguardians.data.remote.dto.TherapistByIdDto
import org.guardteam.mentalguardians.data.remote.dto.TherapistDto
import org.guardteam.mentalguardians.data.remote.dto.TransactionDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
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

    @GET("predict")
    suspend fun historyPredict(
        @Query("userId") userId: String
    ): HistoryDto

    @GET("content-recommender")
    suspend fun contentById(
        @Query("contentId") contentId: String
    ): ContentByIdDto

    @FormUrlEncoded
    @POST("expert-recommender")
    suspend fun expertRecommender(
        @Field("expert") expert: String
    ): TherapistDto

    @GET("expert-recommender")
    suspend fun expertById(
        @Query("therapistId") therapistId: String
    ): TherapistByIdDto

    @FormUrlEncoded
    @POST("booking")
    suspend fun booking(
        @Field("userId") userId: String,
        @Field("therapistId") therapistId: String,
        @Field("tanggal_konseling") date: String,
        @Field("jam_konseling") time: String,
        @Field("jenis_konseling") method: String
    ): ResponseDto

    @GET("profile")
    suspend fun profile(
        @Query("userId") userId: String
    ): ProfileDto

    @GET("booking")
    suspend fun transaction(
        @Query("userId") userId: String
    ): TransactionDto

    @FormUrlEncoded
    @PUT("profile")
    suspend fun editProfile(
        @Query("userId") userId: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("alamat") alamat: String
      ): ResponseDto
      
    @PUT("booking")
    suspend fun cancelBooking(
        @Query("bookingId") bookingId: String
    ): ResponseDto
}