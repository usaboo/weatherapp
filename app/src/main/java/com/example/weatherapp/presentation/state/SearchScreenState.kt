package com.example.weatherapp.presentation.state

import com.example.weatherapp.domain.model.LocationModel

data class SearchScreenState(
    val data: List<LocationModel> = emptyList(),
    val isLoading: Boolean = false,
    val message: String? = null
)
