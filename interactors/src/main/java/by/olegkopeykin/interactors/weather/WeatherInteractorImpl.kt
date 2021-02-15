package by.olegkopeykin.interactors.weather

import by.olegkopeykin.interactors.cities.CityInteractor
import by.olegkopeykin.model.domain.CityModel
import by.olegkopeykin.model.domain.WeatherModel
import by.olegkopeykin.model.toDomain
import by.olegkopeykin.services.database.dao.WeatherDao
import by.olegkopeykin.services.network.api.WeatherApi
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class WeatherInteractorImpl(
    private val weatherDao: WeatherDao,
    private val interactorCity: CityInteractor,
    private val weatherApi: WeatherApi) : WeatherInteractor{

    override fun getCityWeatherNow(city: CityModel): Single<WeatherModel> {
        return weatherApi.getCityWeatherNow(lat = city.lat, lon = city.lon)
            .map {
                it.toDomain(city)
            }
            .onErrorReturn { WeatherModel.NONE }
            .flatMap { weather->
                if(weather == WeatherModel.NONE){
                    return@flatMap Single.just(weather)
                }else{
                    weatherDao.getWeatherEntityByParams(weather.lat, weather.lon, weather.nameCity)
                        .flatMapCompletable {
                            if(!it.isNullOrEmpty()){
                                weatherDao.removeWeathersCity(weather.lat, weather.lon, weather.nameCity)
                                    .andThen(weatherDao.saveWeather(weather.toDomain()))
                            }else{
                                weatherDao.saveWeather(weather.toDomain())
                            }
                        }
                        .andThen(Single.just(weather))
                }
            }
            .subscribeOn(Schedulers.io())
    }

    override fun getCityWeatherOn7Days(city: CityModel): Single<List<WeatherModel>> {
        return weatherApi.getCityWeatherOn7Days(lat = city.lat, lon = city.lon)
            .map {
                it.daily?.map {
                    it.toDomain(city)
                }?: listOf()
            }
            .onErrorReturn { emptyList() }
            .subscribeOn(Schedulers.io())
    }

    override fun getWeatherNowCitiesFromDB(): Observable<List<WeatherModel>> {
        return weatherDao.getWeatherNow()
            .map {
                it.map { it.toDomain() }
            }
            .toObservable()
            .subscribeOn(Schedulers.io())
    }

    override fun getWeatherNowForCities(listCities: List<CityModel>): Single<List<WeatherModel>> {
        return Single.just(listCities)
            .map { list ->
                list.map {
                    getCityWeatherNow(it).toObservable()
                        .subscribeOn(Schedulers.io())
                }.toList()
            }
            .flatMap { listObs->
                Observable.merge(listObs)
                    .toList()
            }
            .subscribeOn(Schedulers.io())
    }
}