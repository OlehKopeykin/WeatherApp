package by.olegkopeykin.interactors.cities

import by.olegkopeykin.interactors.pref.PrefInteractor
import by.olegkopeykin.model.db.CityEntity
import by.olegkopeykin.model.domain.CityModel
import by.olegkopeykin.model.toDomain
import by.olegkopeykin.services.database.dao.CityDao
import by.olegkopeykin.services.network.api.CityApi
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityInteractorImpl @Inject constructor(
    private val cityApi: CityApi,
    private val cityDao: CityDao,
    private val prefInteractor: PrefInteractor
) : CityInteractor {

    override fun getCityByName(name: String): Single<List<CityModel>> {
        return if (name.isEmpty()) {
            Single.just(emptyList())
        } else {
            cityApi.getCitiesByName(requestNameCity = name)
                .map { list ->
                    val grouping = list.groupBy { it.country }
                    val listResult =
                        when {
                            grouping.size >= 2 -> grouping.map { item -> item.value.first() }
                            grouping.size == 1 -> listOf(grouping.values.first().first())
                            else -> emptyList()
                        }
                    listResult.map { it.toDomain() }
                }
                .onErrorReturn { listOf() }
                .subscribeOn(Schedulers.io())
        }
    }

    override fun setFavoriteCity(city: CityModel): Completable {
        return cityDao.getCityByParams(city.lat, city.lon, city.name)
            .flatMapCompletable { list ->
                if (!list.isNullOrEmpty()) {
                    val cityEntity = list.first()
                    cityDao.updateCity(cityEntity.copy(isFavoriteCity = !city.isFavorite))
                } else {
                    Completable.complete()
                }
            }
            .subscribeOn(Schedulers.io())
    }

    override fun addDefaultCities(): Completable {
        return Observables.combineLatest(
            getCityByName("Minsk, BY").toObservable(),
            getCityByName("Gomel,BY").toObservable(),
            getCityByName("Grodno, BY").toObservable(),
            getCityByName("Mogilev, BY").toObservable(),
            getCityByName("Brest, BY").toObservable(),
            getCityByName("Vitebsk, BY").toObservable()
        )
        { listMinsk, listGomel, listGrodno, listMogilev, listBrest, listVitebst ->

            val listCities = ArrayList<CityEntity>()
            if (!listMinsk.isNullOrEmpty()) {
                listCities.add(listMinsk.first().toDomain())
            }
            if (!listGomel.isNullOrEmpty()) {
                listCities.add(listGomel.first().toDomain())
            }
            if (!listGrodno.isNullOrEmpty()) {
                listCities.add(listGrodno.first().toDomain())
            }
            if (!listMogilev.isNullOrEmpty()) {
                listCities.add(listMogilev.first().toDomain())
            }
            if (!listBrest.isNullOrEmpty()) {
                listCities.add(listBrest.first().toDomain())
            }
            if (!listVitebst.isNullOrEmpty()) {
                listCities.add(listVitebst.first().toDomain())
            }
            listCities
        }
            .switchMapCompletable {
                if (!it.isNullOrEmpty()) {
                    prefInteractor.setFirstInit()
                }
                cityDao.removeAllCities().andThen(cityDao.saveCities(it))
            }
            .subscribeOn(Schedulers.io())

    }

    override fun removeCityDB(city: CityModel): Completable {
        return cityDao.removeCity(city.lat, city.lon, city.name)
            .subscribeOn(Schedulers.io())
    }

    override fun saveCityDB(city: CityModel): Completable {
        return cityDao.getCityByParams(city.lat, city.lon, city.name)
            .flatMapCompletable {
                if (it.isNullOrEmpty()) {
                    cityDao.saveCity(city.toDomain())
                } else {
                    Completable.complete()
                }
            }
            .subscribeOn(Schedulers.io())
    }

    override fun getCities(): Observable<List<CityModel>> {
        return cityDao.getCities()
            .map { list ->
                list.map { it.toDomain() }
            }
            .toObservable()
            .subscribeOn(Schedulers.io())
    }
}