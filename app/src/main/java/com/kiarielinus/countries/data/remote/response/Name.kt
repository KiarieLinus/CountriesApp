package com.kiarielinus.countries.data.remote.response


import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("common")
    val common: String,
    @SerializedName("nativeName")
    val nativeName: HashMap<String,NativeNameInfo> = HashMap(),
    @SerializedName("official")
    val official: String
)