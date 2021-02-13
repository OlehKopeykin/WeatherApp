package by.olegkopeykin.interactors.cities

import by.olegkopeykin.model.domain.CityModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface CityInteractor{

    fun getCityByName(name:String): Single<List<CityModel>>

    fun getCities():Observable<List<CityModel>>
    fun saveCityDB(city: CityModel): Completable
    fun removeCityDB(city: CityModel): Completable

    fun addDefaultCities():Completable

    fun setFavoriteCity(city: CityModel):Completable
}