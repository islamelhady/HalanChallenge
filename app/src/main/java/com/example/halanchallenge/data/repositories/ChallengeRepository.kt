package com.example.halanchallenge.data.repositories

import com.example.halanchallenge.data.model.LoginResponse
import com.example.halanchallenge.data.remote.ChallengeApiService
import com.example.halanchallenge.data.remote.SafeApiRequest

class ChallengeRepository(private val apiService: ChallengeApiService) : SafeApiRequest(){

    suspend fun userLogin(username: String, password: String): LoginResponse {
        return apiRequest { apiService.userLogin(username, password) }
    }
}