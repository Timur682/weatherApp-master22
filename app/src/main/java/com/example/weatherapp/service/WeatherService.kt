package com.example.weatherapp.service

import com.example.weatherapp.models.DataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {


    @GET("forecast.json")
    suspend fun getWeatherList(
        @Query("key")  key:String,
        @Query("q")  q:String,
        @Query("days")  days:String,
        @Query("aqi") aqi:String,
        @Query("alerts") alerts:String,

    ):DataModel

}