package com.kiarielinus.countries.data.remote.response


import com.google.gson.annotations.SerializedName

data class PostalCode(
    @SerializedName("format")
    val format: String,
    @SerializedName("regex")
    val regex: String
)