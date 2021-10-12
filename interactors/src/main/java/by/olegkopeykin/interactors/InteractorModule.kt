package by.olegkopeykin.interactors

import by.olegkopeykin.interactors.cities.CityInteractor
import by.olegkopeykin.interactors.cities.CityInteractorImpl
import by.olegkopeykin.interactors.pref.PrefInteractor
import by.olegkopeykin.interactors.pref.PrefInteractorImpl
import by.olegkopeykin.interactors.weather.WeatherInteractor
import by.olegkopeykin.interactors.weather.WeatherInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface InteractorModule {
    @Binds
    fun getPrefInteractor(prefInteractorImpl: PrefInteractorImpl): PrefInteractor

    @Binds
    fun getCityInteractor(cityInteractorImpl: CityInteractorImpl): CityInteractor

    @Binds
    fun getWeatherInteractor(weatherInteractorImpl: WeatherInteractorImpl): WeatherInteractor
}