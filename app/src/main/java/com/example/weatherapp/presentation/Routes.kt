package com.example.weatherapp.presentation

sealed class Routes(val destination: String) {
    object SearchPage : Routes("search_screen")
    object WeatherPage: Routes("weather_screen")
}