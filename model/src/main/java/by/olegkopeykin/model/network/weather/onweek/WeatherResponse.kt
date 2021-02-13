package by.olegkopeykin.model.network.weather.onweek

import by.olegkopeykin.model.network.weather.onweek.Daily
import by.olegkopeykin.model.network.weather.onweek.Hourly

data class WeatherResponse(
    val lat : Double = 0.0,
    val lon : Double = 0.0,
    val timezone: String? = null,
    val timezone_offset: Int  = 0,
    val hourly: List<Hourly>? = null,
    val daily: List<Daily>? = null
)

