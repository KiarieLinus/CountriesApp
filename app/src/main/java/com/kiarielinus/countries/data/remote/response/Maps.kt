package com.kiarielinus.countries.data.remote.response


import com.google.gson.annotations.SerializedName

data class Maps(
    @SerializedName("googleMaps")
    val googleMaps: String,
    @SerializedName("openStreetMaps")
    val openStreetMaps: String
)