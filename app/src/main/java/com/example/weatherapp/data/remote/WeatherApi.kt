package com.example.weatherapp.data.remote

import com.example.weatherapp.data.remote.dto.WeatherDto
import com.example.weatherapp.domain.model.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/v1/forecast")
    suspend fun getForecast(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") current: String = "temperature_2m,apparent_temperature,weather_code",
        @Query("hourly") hourly: String = "temperature_2m,precipitation_probability,weather_code",
        @Query("daily") daily: String = "weather_code,temperature_2m_max,temperature_2m_min,sunrise,sunset",
        @Query("temperature_unit") tempUnit: String = "fahrenheit",
        @Query("timezone") timeZone: String = "auto",
    ): WeatherDto
}



