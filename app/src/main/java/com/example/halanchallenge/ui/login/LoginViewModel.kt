package com.example.halanchallenge.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.halanchallenge.data.model.LoginResponse
import com.example.halanchallenge.data.repositories.ChallengeRepository
import com.example.halanchallenge.util.Resource
import com.example.halanchallenge.util.ValidateMethods
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: ChallengeRepository
) : ViewModel() {

    val userResource: Flow<Resource<LoginResponse>>? = null

    private val _loginEventChannel = Channel<LoginEvent>()
    val loginEventChannel = _loginEventChannel.receiveAsFlow()

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

        if (_errorMessage != "") onShowErrorMessage(_errorMessage)

        return _errorMessage.isEmpty()
    }


    fun loginUser() {
        viewModelScope.launch {
            repository.userLogin(username, password)
        }
    }

    fun onShowErrorMessage(message: String) {
        viewModelScope.launch {
            _loginEventChannel.send(LoginEvent.ShowErrorMessage(message))
        }
    }
    fun onSuccessfulLogin(response: LoginResponse) {
        viewModelScope.launch {
            _loginEventChannel.send(LoginEvent.LoginCompletedEvent(response))
        }
    }

    sealed class LoginEvent {
        data class ShowErrorMessage(val message: String) : LoginEvent()
        data class LoginCompletedEvent(val response: LoginResponse) : LoginEvent()
    }
}
