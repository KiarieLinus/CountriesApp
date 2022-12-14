package com.kiarielinus.countries.data.remote.response

import com.google.gson.annotations.SerializedName

data class CurrencyInfo(
    @SerializedName("name")
    val name: String?,
    @SerializedName("symbol")
    val symbol: String?
)
