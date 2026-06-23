package com.example.weatherapp.domain.model

data class DailyDataModel(
    val sunrise: List<String>,
    val sunset: List<String>,
    val temperatureMax: List<Double>,
    val temperatureMin: List<Double>,
    val date: List<String>,
)
