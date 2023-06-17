package com.example.weatherapp.models

data class ForecastModel(
    val forecastday: List<ForecastDayModel>

)
data class ForecastDayModel(
    val date: String,
    val day: DayModel,
    val hour: List<HourModel>
)

data class DayModel(
    val maxtemp_c: String,
    val mintemp_c: String,
    val condition: Condition
)

data class HourModel(
    val time: String,
    val temp_c: Float,
    val condition: Condition
)