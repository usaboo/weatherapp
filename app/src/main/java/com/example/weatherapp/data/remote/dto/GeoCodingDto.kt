package com.example.weatherapp.data.remote.dto
import com.example.weatherapp.domain.model.LocationModel

data class GeoCodingDto(
    val generationtime_ms: Double,
    val results: List<Result>
)

fun GeoCodingDto.toLocationModels(): List<LocationModel> {
    return results.map {
        LocationModel(
            name = it.name,
            country = it.country,
            latitude = it.latitude,
            longitude = it.longitude
        )
    }
}