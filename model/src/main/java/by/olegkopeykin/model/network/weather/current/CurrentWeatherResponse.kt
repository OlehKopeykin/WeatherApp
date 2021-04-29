package by.olegkopeykin.model.network.weather.current

data class CurrentWeatherResponse(
    val coordinates: Coordinates? = null,
    val weather: List<Weather>? = null,
    val base: String? = null,
    val main: Main? = null,
    val visibility: Int = 0,
    val wind: Wind? = null,
    val clouds: Clouds? = null,
    val dt: Long = 0L,
    val sys: Sys? = null,
    val timezone: Int = 0,
    val id: Int = 0,
    val name: String? = null,
    val cod: Int = 0
)



