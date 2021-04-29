package by.olegkopeykin.interactors.cities

import by.olegkopeykin.model.domain.CityModel
import kotlinx.coroutines.flow.Flow

interface CityInteractor {

	suspend fun getCityByName(name: String): List<CityModel>

	fun getCitiesDB(): Flow<List<CityModel>>
	suspend fun saveCityDB(city: CityModel)
	suspend fun removeCityDB(city: CityModel)

	suspend fun addDefaultCities()

	suspend fun setFavoriteCity(city: CityModel)
}