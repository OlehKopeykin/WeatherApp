package by.olegkopeykin.interactors.weather

import by.olegkopeykin.model.domain.CityModel
import by.olegkopeykin.model.domain.WeatherModel
import io.reactivex.Observable
import io.reactivex.Single

interface WeatherInteractor {

    fun getCityWeatherNow(city:CityModel): Single<WeatherModel>
    fun getCityWeatherOn7Days(city:CityModel): Single<List<WeatherModel>>
    fun getWeatherNowForCities(listCities: List<CityModel>): Single<List<WeatherModel>>
}