package com.example.weatherapp.presentation.state

import com.example.weatherapp.domain.model.WeatherModel

data class WeatherScreenState(
    val data: WeatherModel? = null,
    val isLoading: Boolean = false,
    val message: String?  = null,
)
