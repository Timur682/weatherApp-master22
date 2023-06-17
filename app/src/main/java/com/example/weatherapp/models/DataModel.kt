package com.example.weatherapp.models

data class DataModel(
    val location: Local,
    val current: CurrentModel,
    val forecast: ForecastModel
)

data class Local(
    val name: String,
    val localtime: String
)

data class CurrentModel(
    val last_updated: String,
    val temp_c: Float,
    val condition: Condition
)

data class Condition(
    val text: String,
    val icon: String
)


