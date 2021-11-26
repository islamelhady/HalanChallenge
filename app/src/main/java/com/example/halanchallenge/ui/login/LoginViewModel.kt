package com.example.halanchallenge.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.halanchallenge.data.repositories.ChallengeRepository
import com.example.halanchallenge.util.ValidateMethods
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val repository: ChallengeRepository) : ViewModel() {




    var username = ""
        set(value) {
            field = value
        }
    var password = ""
        set(value) {
            field = value
        }

    fun validateFields(): Boolean {
        var _errorMessage = ""

        _errorMessage += if (_errorMessage != "") {
            "\n${ValidateMethods.validateUsername(username)}"
        } else {
            ValidateMethods.validateUsername(username)
        }
        _errorMessage += if (_errorMessage != "") {
            "\n${ValidateMethods.validatePassword(password)}"
        } else {
            ValidateMethods.validatePassword(password)
        }


        return _errorMessage.isEmpty()
    }

    suspend fun userLogin() =
        withContext(Dispatchers.IO) { repository.userLogin(username, password) }

    fun loginUser() {
        viewModelScope.launch {
            repository.userLogin(username, password)
        }
    }
}