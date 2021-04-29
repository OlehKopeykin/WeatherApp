package by.olegkopeykin.services.network.api

import by.olegkopeykin.model.network.weather.current.CurrentWeatherResponse
import by.olegkopeykin.model.network.weather.onweek.WeatherResponse
import by.olegkopeykin.services.network.NetworkServiceImpl
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

	@GET("data/2.5/weather")
	suspend fun getCityWeatherNow(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String = NetworkServiceImpl.KEY_API
    ): CurrentWeatherResponse

	@GET("data/2.5/onecall")
	suspend fun getCityWeatherOn7Days(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String = NetworkServiceImpl.KEY_API
    ): WeatherResponse
}
