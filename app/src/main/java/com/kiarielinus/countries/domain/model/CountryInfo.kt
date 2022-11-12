package com.kiarielinus.countries.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryInfo(
    val name: String,
    val capital: String,
    val population: Int,
    val area: Double,
    val demonyms: String,
    val unMember: Boolean,
    val diallingCode: String,
    val landlocked: Boolean,
    val countryDomain: List<String>,
    val timezone: List<String>,
    val startOfWeek: String,
    val drivingSide: String,
    val independent: Boolean,
    val continent: List<String>,
    val flagUrl: String,
    val coatOfArmsUrl: String?,
    val mapUrl: String,
    val currencyName: String,
    val currencySymbol: String
) : Parcelable