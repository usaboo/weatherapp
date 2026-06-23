package com.example.weatherapp.presentation.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.weatherapp.common.Resource
import com.example.weatherapp.domain.use_cases.SearchLocationUseCase
import com.example.weatherapp.presentation.state.SearchScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.use_cases.SearchWeatherUseCase
import com.example.weatherapp.presentation.state.WeatherScreenState
import kotlinx.coroutines.flow.launchIn

@HiltViewModel
class WeatherScreenViewModel @Inject constructor(
    private val searchWeatherUseCase: SearchWeatherUseCase,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    private val _state = mutableStateOf(WeatherScreenState())
    val state: State<WeatherScreenState> = _state

    init {
        savedStateHandle.get<String>("longitude")?.let { longitude ->
            savedStateHandle.get<String>("latitude")?.let { latitude ->
                getWeather(latitude.toDouble(), longitude.toDouble())
            }
        }
    }

    fun getWeather(latitude: Double, longitude: Double) {
        searchWeatherUseCase(latitude, longitude).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    _state.value = WeatherScreenState(resource.data)
                }

                is Resource.Error -> {
                    _state.value = WeatherScreenState(
                        message = resource.message ?: "an unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _state.value = WeatherScreenState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}