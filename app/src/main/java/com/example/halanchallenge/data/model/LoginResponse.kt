package com.example.halanchallenge.data.model

import android.os.Parcelable
import com.example.halanchallenge.data.model.Profile
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginResponse(
    val token: String? = null,
    val profile: Profile? = null
): Parcelable

