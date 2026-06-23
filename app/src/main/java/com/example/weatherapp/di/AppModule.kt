package com.example.weatherapp.di

import com.example.weatherapp.data.remote.GeoCodingApi
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGeoCodingApi(): GeoCodingApi {
        return Retrofit.Builder()
            .baseUrl("https://geocoding-api.open-meteo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GeoCodingApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(
        geoCodingApi: GeoCodingApi,
        weatherApi: WeatherApi
    ): WeatherRepository {
        return WeatherRepositoryImpl(geoCodingApi, weatherApi)
    }
}