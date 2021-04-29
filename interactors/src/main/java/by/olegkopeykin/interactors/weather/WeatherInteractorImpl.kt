package by.olegkopeykin.interactors.weather

import by.olegkopeykin.model.domain.CityModel
import by.olegkopeykin.model.domain.WeatherModel
import by.olegkopeykin.model.toDomain
import by.olegkopeykin.model.toEntity
import by.olegkopeykin.services.database.dao.WeatherDao
import by.olegkopeykin.services.network.api.WeatherApi
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
class WeatherInteractorImpl(
	private val weatherDao: WeatherDao,
	private val weatherApi: WeatherApi
) : WeatherInteractor {

	override suspend fun getCityWeatherNow(city: CityModel): WeatherModel = withContext(Dispatchers.IO) {
		val weather = weatherApi.getCityWeatherNow(lat = city.lat, lon = city.lon).toDomain(city)
		weatherDao.getWeatherEntityByParams(weather.city.lat, weather.city.lon, weather.city.name)
			.let {
				if (!it.isNullOrEmpty()) {
					weatherDao.removeWeathersCity(weather.city.lat, weather.city.lon, weather.city.name)
				}
				weatherDao.saveWeather(weather.toEntity())
			}
		weather
	}

	override suspend fun getCityWeatherOn7Days(city: CityModel): List<WeatherModel> = withContext(Dispatchers.IO) {
		weatherApi.getCityWeatherOn7Days(lat = city.lat, lon = city.lon)
			.daily?.map { it.toDomain(city) } ?: listOf()
	}

	override suspend fun getWeatherNowForCities(listCities: List<CityModel>): List<WeatherModel> = withContext(Dispatchers.IO) {
		listCities.map {
			async { getCityWeatherNow(it) }
		}.awaitAll()
	}

	override fun getWeatherNowCitiesFromDB(): Flow<List<WeatherModel>> {
		return weatherDao.getWeatherNow()
			.map { listWeather ->
				listWeather.map { it.toDomain() }
			}.flowOn(Dispatchers.IO)
	}
}