package by.olegkopeykin.services.database.dao

import androidx.room.*
import by.olegkopeykin.model.db.WeatherEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface WeatherDao {

    @Query("select * from WeatherEntity")
    fun getWeatherNow(): Flowable<List<WeatherEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveWeather(weatherEntity: WeatherEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveWeathers(weatherEntity: List<WeatherEntity>): Completable

    @Query("delete from WeatherEntity where lat = :lat and lon = :lon and nameCity = :name")
    fun removeWeathersCity(lat:Double, lon:Double, name:String):Completable

    @Query("select * from WeatherEntity where lat = :lat and lon = :lon and nameCity = :name")
    fun getWeatherEntityByParams(lat:Double, lon:Double, name:String): Single<List<WeatherEntity>>
}