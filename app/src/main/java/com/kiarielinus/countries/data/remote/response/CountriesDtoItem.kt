package com.kiarielinus.countries.data.remote.response


import com.google.gson.annotations.SerializedName
import com.kiarielinus.countries.domain.model.CountryInfo

data class CountriesDtoItem(
    @SerializedName("altSpellings")
    val altSpellings: List<String>,
    @SerializedName("area")
    val area: Double,
    @SerializedName("borders")
    val borders: List<String>?,
    @SerializedName("capital")
    val capital: List<String>?,
    @SerializedName("capitalInfo")
    val capitalInfo: CapitalInfo?,
    @SerializedName("car")
    val car: Car,
    @SerializedName("cca2")
    val cca2: String,
    @SerializedName("cca3")
    val cca3: String,
    @SerializedName("ccn3")
    val ccn3: String?,
    @SerializedName("cioc")
    val cioc: String,
    @SerializedName("coatOfArms")
    val coatOfArms: CoatOfArms?,
    @SerializedName("continents")
    val continents: List<String>,
    @SerializedName("currencies")
    val currencies: HashMap<String, CurrencyInfo>? = HashMap(),
    @SerializedName("demonyms")
    val demonyms: Demonyms?,
    @SerializedName("fifa")
    val fifa: String?,
    @SerializedName("flag")
    val flag: String,
    @SerializedName("flags")
    val flags: Flags,
    @SerializedName("gini")
    val gini: HashMap<String,Double> = HashMap(),
    @SerializedName("idd")
    val idd: Idd?,
    @SerializedName("independent")
    val independent: Boolean,
    @SerializedName("landlocked")
    val landlocked: Boolean,
    @SerializedName("languages")
    val languages: HashMap<String,String>? = HashMap(),
    @SerializedName("latlng")
    val latlng: List<Double>,
    @SerializedName("maps")
    val maps: Maps,
    @SerializedName("name")
    val name: Name,
    @SerializedName("population")
    val population: Int,
    @SerializedName("postalCode")
    val postalCode: PostalCode?,
    @SerializedName("region")
    val region: String,
    @SerializedName("startOfWeek")
    val startOfWeek: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("subregion")
    val subregion: String?,
    @SerializedName("timezones")
    val timezones: List<String>,
    @SerializedName("tld")
    val tld: List<String>?,
    @SerializedName("translations")
    val translations: HashMap<String,TranslationInfo>? = HashMap(),
    @SerializedName("unMember")
    val unMember: Boolean
){
    fun toCountryInfo(): CountryInfo{
        return CountryInfo(
            name = name.common,
            capital = capital?.first() ?: "Unspecified",
            population = population,
            continent = continents,
            area = area,
            demonyms = demonyms?.eng?.f ?: "Unspecified",//English doesn't have grammatical gender
            unMember = unMember,
            diallingCode = "${idd?.root}${idd?.suffixes?.first()}",
            landlocked = landlocked,
            countryDomain = tld ?: emptyList(),
            timezone = timezones,
            startOfWeek = startOfWeek,
            drivingSide = car.side,
            independent = independent,
            flagUrl = flags.png,
            coatOfArmsUrl = coatOfArms?.png,
            mapUrl = maps.openStreetMaps,
            currencyName = currencies?.values?.first()?.name ?: "",
            currencySymbol = currencies?.values?.first()?.symbol ?: ""
        )
    }
}