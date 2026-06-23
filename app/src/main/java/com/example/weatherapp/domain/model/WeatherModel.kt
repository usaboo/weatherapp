package com.example.weatherapp.domain.model

data class WeatherModel(
    val latitude: Double,
    val longitude: Double,
    val hourlyData: HourlyDataModel,
    val dailyData: DailyDataModel,
)
