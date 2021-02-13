package by.olegkopeykin.services.network.api

import by.olegkopeykin.model.network.weather.current.CurrentWeatherResponse
import by.olegkopeykin.model.network.weather.onweek.WeatherResponse
import by.olegkopeykin.services.network.NetworkServiceImpl
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather")
    fun getCityWeatherNow(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String = NetworkServiceImpl.KEY_API): Single<CurrentWeatherResponse>

    @GET("data/2.5/onecall")
    fun getCityWeatherOn7Days(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String = NetworkServiceImpl.KEY_API): Single<WeatherResponse>
}
