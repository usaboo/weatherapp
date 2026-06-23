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
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn

@HiltViewModel
class SearchScreenViewModel @Inject constructor(private val searchLocationUseCase: SearchLocationUseCase) :
    ViewModel() {
    private val _state = mutableStateOf(SearchScreenState())
    val state: State<SearchScreenState> = _state

    fun getCities(cityName: String) {
        searchLocationUseCase(cityName).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    _state.value = SearchScreenState(resource.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = SearchScreenState(
                        message = resource.message ?: "an unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _state.value = SearchScreenState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}