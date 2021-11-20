package com.example.halanchallenge.data.remote

import com.example.halanchallenge.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ChallengeApiService {

    @FormUrlEncoded
    @POST("auth")
    suspend fun userLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ) : Response<LoginResponse>



    companion object {
        const val BASE_URL = "https://assessment-sn12.halan.io/"
    }
}