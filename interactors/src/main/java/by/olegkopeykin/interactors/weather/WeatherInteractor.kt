package by.olegkopeykin.interactors.weather

import by.olegkopeykin.model.domain.CityModel
import by.olegkopeykin.model.domain.WeatherModel
import kotlinx.coroutines.flow.Flow

interface WeatherInteractor {
	suspend fun getCityWeatherOn7Days(city: CityModel): List<WeatherModel>

	suspend fun updateCityWeatherNow(city: CityModel): WeatherModel
	suspend fun updateCitiesWeatherNow(listCities: List<CityModel>): List<WeatherModel>

	fun getCitiesWeatherNowFromDB(): Flow<List<WeatherModel>>
}