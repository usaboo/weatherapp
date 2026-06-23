package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.model.LocationModel
import com.example.weatherapp.domain.model.WeatherModel

interface WeatherRepository {
    suspend fun getLocation(cityName: String): List<LocationModel>

    suspend fun getWeather(latitude: Double, longitude: Double): WeatherModel

}