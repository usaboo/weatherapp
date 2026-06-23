package com.example.weatherapp.data.remote.dto

data class HourlyUnits(
    val precipitation_probability: String,
    val temperature_2m: String,
    val time: String,
    val weather_code: String
)