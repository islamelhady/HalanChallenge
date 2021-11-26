package com.example.halanchallenge.util

object ValidateMethods {

    fun validateUsername(username: String): String {
        val usernameInput: String = username.trim()
        return when {
            usernameInput.isEmpty() -> {
                "Username field can't be empty"
            }
            usernameInput.length < 6 -> {
                "Username must be at least 5 characters long"
            }
            usernameInput.length > 15 -> {
                "Username can be up to 15 characters long"
            }
            else -> {
                ""
            }
        }
    }

    fun validatePassword(username: String): String {
        val usernameInput: String = username.trim()
        return when {
            usernameInput.isEmpty() -> {
                "Password field can't be empty"
            }
            usernameInput.length < 5 -> {
                "Password must be at least 5 characters long"
            }
            else -> {
                ""
            }
        }
    }
}