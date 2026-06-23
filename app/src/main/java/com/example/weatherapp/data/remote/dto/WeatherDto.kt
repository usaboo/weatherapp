package com.example.weatherapp.data.remote.dto

import com.example.weatherapp.domain.model.DailyDataModel
import com.example.weatherapp.domain.model.HourlyDataModel
import com.example.weatherapp.domain.model.WeatherModel

data class WeatherDto(
    val current: Current,
    val current_units: CurrentUnits,
    val daily: Daily,
    val daily_units: DailyUnits,
    val elevation: Int,
    val generationtime_ms: Double,
    val hourly: Hourly,
    val hourly_units: HourlyUnits,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
)

fun WeatherDto.toWeatherModel(): WeatherModel {
    return WeatherModel(
        latitude = this.latitude,
        longitude = this.longitude,
        hourlyData = HourlyDataModel(this.hourly.temperature_2m, this.hourly.time),
        dailyData = DailyDataModel(
            sunset = this.daily.sunrise,
            sunrise = this.daily.sunset,
            date = this.daily.time,
            temperatureMax = this.daily.temperature_2m_max,
            temperatureMin = this.daily.temperature_2m_min
        )
    )
}