package com.example.weatherapp.domain.model

data class HourlyDataModel(
    val temperature: List<Double>,
    val time: List<String>,
)
