package com.example.weatherapp.data.repository

import com.example.weatherapp.data.remote.GeoCodingApi
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.data.remote.dto.toLocationModels
import com.example.weatherapp.data.remote.dto.toWeatherModel
import com.example.weatherapp.domain.model.LocationModel
import com.example.weatherapp.domain.model.WeatherModel
import com.example.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val geoCodingApi: GeoCodingApi,
    private val weatherApi: WeatherApi
) : WeatherRepository {
    override suspend fun getLocation(cityName: String): List<LocationModel> {
        return geoCodingApi.searchLocation(cityName).toLocationModels()
    }

    override suspend fun getWeather(latitude: Double, longitude: Double): WeatherModel {
        return weatherApi.getForecast(latitude, longitude).toWeatherModel()
    }
}