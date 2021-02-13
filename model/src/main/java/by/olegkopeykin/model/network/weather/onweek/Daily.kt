package by.olegkopeykin.model.network.weather.onweek

data class Daily(
    val dt : Long= 0L,
    val sunrise : Int = 0,
    val sunset : Int = 0,
    val temp: Temp? = null,
    val feels_like: FeelsLike? = null,
    val pressure : Int = 0,
    val humidity : Int = 0,
    val dew_point : Double = 0.0,
    val wind_speed : Double = 0.0,
    val wind_deg : Int = 0,
    val weather: List<Weather>? = null,
    val clouds : Int = 0,
    val pop : Double = 0.0,
    val snow : Double = 0.0,
    val uvi : Double = 0.0,
    val rain : Double = 0.0
)