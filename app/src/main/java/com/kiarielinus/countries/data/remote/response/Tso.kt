package com.kiarielinus.countries.data.remote.response


import com.google.gson.annotations.SerializedName

data class Tso(
    @SerializedName("common")
    val common: String,
    @SerializedName("official")
    val official: String
)