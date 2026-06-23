package com.example.weatherapp.domain.use_cases

import com.example.weatherapp.common.Resource
import com.example.weatherapp.data.remote.GeoCodingApi
import com.example.weatherapp.domain.model.LocationModel
import com.example.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchLocationUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    operator fun invoke(cityName: String): Flow<Resource<List<LocationModel>>> = flow {
        try {
            emit(Resource.Loading())
            val locations = weatherRepository.getLocation(cityName)
            emit(Resource.Success(locations))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "an unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "an unexpected error occured"))
        }
    }
}