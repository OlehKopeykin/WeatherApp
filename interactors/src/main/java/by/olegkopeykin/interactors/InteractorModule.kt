package by.olegkopeykin.interactors

import by.olegkopeykin.interactors.cities.CityInteractor
import by.olegkopeykin.interactors.cities.CityInteractorImpl
import by.olegkopeykin.interactors.pref.PrefInteractor
import by.olegkopeykin.interactors.pref.PrefInteractorImpl
import by.olegkopeykin.interactors.weather.WeatherInteractor
import by.olegkopeykin.interactors.weather.WeatherInteractorImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

@ExperimentalCoroutinesApi
val interactorsModule = Kodein.Module("Interactors") {

	bind<PrefInteractor>() with singleton { PrefInteractorImpl(instance()) }
	bind<CityInteractor>() with singleton { CityInteractorImpl(instance(), instance(), instance()) }

	bind<WeatherInteractor>() with singleton { WeatherInteractorImpl(instance(), instance()) }
}