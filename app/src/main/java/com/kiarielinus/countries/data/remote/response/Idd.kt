package com.kiarielinus.countries.data.remote.response


import com.google.gson.annotations.SerializedName

data class Idd(
    @SerializedName("root")
    val root: String,
    @SerializedName("suffixes")
    val suffixes: List<String>
)