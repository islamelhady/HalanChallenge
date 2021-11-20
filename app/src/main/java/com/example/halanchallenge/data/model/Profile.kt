package com.example.halanchallenge.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Profile(
    var username: String? = null,
    var image: String? = null,
    var name: String? = null,
    var phone: String? = null,
    var email: String? = null
) : Parcelable
