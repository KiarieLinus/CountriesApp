package com.kiarielinus.countries.data.remote.response


import com.google.gson.annotations.SerializedName

data class CapitalInfo(
    @SerializedName("latlng")
    val latlng: List<Double>
)