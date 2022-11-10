package com.kiarielinus.countries.data.remote.response


import com.google.gson.annotations.SerializedName

data class Eng(
    @SerializedName("f")
    val f: String,
    @SerializedName("m")
    val m: String
)