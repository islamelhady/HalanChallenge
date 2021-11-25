package com.example.halanchallenge.data.repositories

import NetworkBoundRepository
import com.example.halanchallenge.data.model.LoginResponse
import com.example.halanchallenge.data.model.ProductsList
import com.example.halanchallenge.data.remote.ChallengeApiService
import com.example.halanchallenge.data.remote.SafeApiRequest
import com.example.halanchallenge.util.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class ChallengeRepository(private val apiService: ChallengeApiService) : SafeApiRequest(){

    suspend fun userLogin(username: String, password: String): LoginResponse {
        return apiRequest { apiService.userLogin(username, password) }
    }

    fun getProductsInfo(token: String?): Flow<State<ProductsList>> {
        return object : NetworkBoundRepository<ProductsList>() {
            override suspend fun fetchFromRemote(): Response<ProductsList> =
                apiService.getProductsInfo(token)
        }.asFlow().flowOn(Dispatchers.IO)
    }
}