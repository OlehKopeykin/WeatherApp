package by.olegkopeykin.services.database.dao

import androidx.room.*
import by.olegkopeykin.model.db.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

	@Query("select * from CityEntity")
	fun getCities(): Flow<List<CityEntity>>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun saveCity(city: CityEntity)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun saveCities(cities: List<CityEntity>)

	@Query("delete from CityEntity")
	suspend fun removeAllCities()

	@Query("delete from CityEntity where lat = :lat and lon = :lon and name = :name")
	suspend fun removeCity(lat: Double, lon: Double, name: String)

	@Update
	suspend fun updateCity(city: CityEntity)

	@Query("select * from CityEntity where lat = :lat and lon = :lon and name = :name")
	suspend fun getCityByParams(lat: Double, lon: Double, name: String): List<CityEntity>
}