package by.olegkopeykin.services.database.dao

import androidx.room.*
import by.olegkopeykin.model.db.WeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

	@Query("select * from WeatherEntity, CityEntity where WeatherEntity.lat = CityEntity.lat and WeatherEntity.lon = CityEntity.lon and WeatherEntity.nameCity = CityEntity.name")
	fun getWeatherNow(): Flow<List<WeatherEntity>>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun saveWeather(weatherEntity: WeatherEntity)

	@Query("delete from WeatherEntity where lat = :lat and lon = :lon and nameCity = :name")
	suspend fun removeWeathersCity(lat: Double, lon: Double, name: String)

	@Query("select * from WeatherEntity where lat = :lat and lon = :lon and nameCity = :name")
	suspend fun getWeatherEntityByParams(
		lat: Double,
		lon: Double,
		name: String
	): List<WeatherEntity>
}