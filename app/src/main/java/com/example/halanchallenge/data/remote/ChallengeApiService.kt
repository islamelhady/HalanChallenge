package com.example.halanchallenge.data.remote

import com.example.halanchallenge.data.model.LoginResponse
import com.example.halanchallenge.data.model.ProductsList
import retrofit2.Response
import retrofit2.http.*

interface ChallengeApiService {

    @FormUrlEncoded
    @POST("auth")
    suspend fun userLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ) : Response<LoginResponse>

    @GET("products")
    suspend fun getProductsInfo(@Header("Authorization") token : String?): Response<ProductsList>


    companion object {
        const val BASE_URL = "https://assessment-sn12.halan.io/"
    }
}