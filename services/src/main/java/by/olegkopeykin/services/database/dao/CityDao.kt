package by.olegkopeykin.services.database.dao

import androidx.room.*
import by.olegkopeykin.model.db.CityEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface CityDao {

    @Query("select * from CityEntity")
    fun getCities(): Flowable<List<CityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCity(city:CityEntity):Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCities(cities: List<CityEntity>): Completable

    @Query("delete from CityEntity")
    fun removeAllCities():Completable

    @Query("delete from CityEntity where lat = :lat and lon = :lon and name = :name")
    fun removeCity(lat:Double, lon:Double, name:String):Completable

    @Update
    fun updateCity(city:CityEntity):Completable

    @Query("select * from CityEntity where lat = :lat and lon = :lon and name = :name")
    fun getCityByParams(lat:Double, lon:Double, name:String): Single<List<CityEntity>>
}