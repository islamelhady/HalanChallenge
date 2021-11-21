package com.example.halanchallenge.ui.login

import androidx.lifecycle.ViewModel
import com.example.halanchallenge.data.repositories.ChallengeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewModel(private val repository: ChallengeRepository) : ViewModel() {

    suspend fun userLogin(
        username: String,
        password: String
    ) = withContext(Dispatchers.IO) { repository.userLogin(username, password) }
}