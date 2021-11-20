package com.example.halanchallenge.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class ProductsList(
    var status: String? = null,
    var products: List<Product>? = null
)

@Parcelize
data class Product(
    var id: Int? = null,
    var name_ar: String? = null,
    var deal_description: String? = null,
    var brand: String? = null,
    var image: String? = null,
    var name_en: String? = null,
    var price: Int? = null,
    var images: List<String>? = null,
) : Parcelable
