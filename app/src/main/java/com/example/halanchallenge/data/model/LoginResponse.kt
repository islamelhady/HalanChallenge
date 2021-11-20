package com.example.halanchallenge.data.model

import com.example.halanchallenge.data.model.Profile

data class LoginResponse(
    val token: String? = null,
    val profile: Profile? = null
)

