package com.kiarielinus.countries.data.remote.response


import com.google.gson.annotations.SerializedName

data class Demonyms(
    @SerializedName("eng")
    val eng: Eng,
    @SerializedName("fra")
    val fra: Fra
)