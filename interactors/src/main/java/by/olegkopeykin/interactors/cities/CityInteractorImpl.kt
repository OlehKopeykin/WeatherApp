package by.olegkopeykin.interactors.cities

import by.olegkopeykin.interactors.pref.PrefInteractor
import by.olegkopeykin.model.domain.CityModel
import by.olegkopeykin.model.network.CityResponse
import by.olegkopeykin.model.toDomain
import by.olegkopeykin.model.toEntity
import by.olegkopeykin.services.database.dao.CityDao
import by.olegkopeykin.services.network.api.CityApi
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class CityInteractorImpl(
	private val cityApi: CityApi,
	private val cityDao: CityDao, private val prefInteractor: PrefInteractor
) : CityInteractor {

	override suspend fun getCityByName(name: String): List<CityModel> =
		withContext(Dispatchers.IO) {
			if (name.isBlank()) {
				emptyList()
			} else {
				val grouping = cityApi.getCitiesByName(name).groupBy { it.country }
				val listResult: List<CityResponse> = when {
					grouping.size >= 2 -> grouping.map { item -> item.value.first() }
					grouping.size == 1 -> listOf(grouping.values.first().first())
					else -> emptyList()
				}
				listResult.map { it.toDomain() }
			}
		}

	override suspend fun setFavoriteCity(city: CityModel) = withContext(Dispatchers.IO) {
		cityDao.getCityByParams(city.lat, city.lon, city.name).let {
			if (it.isNotEmpty()) {
				val cityEntity = it.first()
				cityDao.updateCity(cityEntity.copy(isFavoriteCity = !city.isFavorite))
			}
		}
	}

	override suspend fun addDefaultCities() = withContext(Dispatchers.IO) {
		listOf(
			async { getCityByName("Minsk, BY") },
			async { getCityByName("Gomel,BY") },
			async { getCityByName("Grodno, BY") },
			async { getCityByName("Mogilev, BY") },
			async { getCityByName("Brest, BY") },
			async { getCityByName("Vitebsk, BY") }
		).awaitAll().mapNotNull {
			if (!it.isNullOrEmpty()) {
				it.first().toEntity()
			} else {
				null
			}
		}.let {
			if (!it.isNullOrEmpty()) {
				prefInteractor.setFirstInit()
			}
			cityDao.removeAllCities()
			cityDao.saveCities(it)
		}
	}

	override suspend fun removeCityDB(city: CityModel) = withContext(Dispatchers.IO) {
		cityDao.removeCity(city.lat, city.lon, city.name)
	}

	override suspend fun saveCityDB(city: CityModel) = withContext(Dispatchers.IO) {
		cityDao.getCityByParams(city.lat, city.lon, city.name).let {
			if (it.isNullOrEmpty()) {
				cityDao.saveCity(city.toEntity())
			}
		}
	}

	override fun getCitiesDB(): Flow<List<CityModel>> {
		return cityDao.getCities()
			.map { listCities ->
				listCities.map { it.toDomain() }
			}.flowOn(Dispatchers.IO)
	}
}