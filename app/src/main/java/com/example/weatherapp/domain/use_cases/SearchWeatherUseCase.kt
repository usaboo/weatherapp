package com.example.weatherapp.domain.use_cases

import com.example.weatherapp.common.Resource
import com.example.weatherapp.domain.model.LocationModel
import com.example.weatherapp.domain.model.WeatherModel
import com.example.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.descriptors.PrimitiveKind
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchWeatherUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    operator fun invoke(latitude: Double, longitude: Double): Flow<Resource<WeatherModel>> =
        flow {
            try {
                emit(Resource.Loading())
                val weather = weatherRepository.getWeather(latitude, longitude)
                emit(Resource.Success(weather))
            } catch (e: HttpException) {
                emit(Resource.Error(e.message ?: "an unexpected error occured"))
            } catch (e: IOException) {
                emit(Resource.Error(e.message ?: "an unexpected error occured"))
            }
        }
}