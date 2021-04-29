package by.olegkopeykin.interactors.weather

import by.olegkopeykin.model.domain.CityModel
import by.olegkopeykin.model.domain.WeatherModel
import kotlinx.coroutines.flow.Flow

interface WeatherInteractor {
	suspend fun getCityWeatherNow(city: CityModel): WeatherModel
	suspend fun getCityWeatherOn7Days(city: CityModel): List<WeatherModel>
	suspend fun getWeatherNowForCities(listCities: List<CityModel>): List<WeatherModel>

	fun getWeatherNowCitiesFromDB(): Flow<List<WeatherModel>>
}