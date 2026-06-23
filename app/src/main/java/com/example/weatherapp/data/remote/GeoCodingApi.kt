package com.example.weatherapp.data.remote

import com.example.weatherapp.data.remote.dto.GeoCodingDto
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface GeoCodingApi {
    @GET("v1/search")
    suspend fun searchLocation(
        @Query("name") cityName: String,
        @Query("count") count: Int = 10,
        @Query("language") language: String = "en",
        @Query("format") format: String = "json",
    ): GeoCodingDto
}