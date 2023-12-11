package org.guardteam.mentalguardians.data.remote

import org.guardteam.mentalguardians.data.remote.dto.LoginDto
import org.guardteam.mentalguardians.data.remote.dto.ResponseDto
import retrofit2.http.Field
import retrofit2.http.POST

interface ApiService {

    @POST("register")
    suspend fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): ResponseDto

    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginDto
}