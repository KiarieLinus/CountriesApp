package com.kiarielinus.countries.util

fun getTimeZones(): List<String> {
    val timeZones = "UTC, UTC+00:00, UTC+01:00, UTC+02:00, UTC+03:00, UTC+03:30, UTC+04:00, UTC+04:30, UTC+05:00, UTC+05:30, UTC+05:45, UTC+06:00, UTC+06:30, UTC+07:00, UTC+08:00, UTC+09:00, UTC+09:30, UTC+10:00, UTC+10:30, UTC+11:00, UTC+11:30, UTC+12:00, UTC+12:45, UTC+13:00, UTC+14:00, UTC-01:00, UTC-02:00, UTC-03:00, UTC-03:30, UTC-04:00, UTC-05:00, UTC-06:00, UTC-07:00, UTC-08:00, UTC-09:00, UTC-09:30, UTC-10:00, UTC-11:00, UTC-12:00"
    return timeZones.split(",").map { it.trim() }
}

fun getContinents(): List<String>{
    val continents = "Africa, Antarctica, Asia, Europe, North America, Oceania, South America"
    return continents.split(",").map { it.trim() }
}