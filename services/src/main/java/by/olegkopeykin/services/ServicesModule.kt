package by.olegkopeykin.services

import by.olegkopeykin.services.database.DatabaseProvider
import by.olegkopeykin.services.database.DatabaseProviderImpl
import by.olegkopeykin.services.database.dao.CityDao
import by.olegkopeykin.services.database.dao.WeatherDao
import by.olegkopeykin.services.network.NetworkService
import by.olegkopeykin.services.network.NetworkServiceImpl
import by.olegkopeykin.services.network.api.CityApi
import by.olegkopeykin.services.network.api.WeatherApi
import by.olegkopeykin.services.preferences.PreferencesHelper
import by.olegkopeykin.services.preferences.PreferencesHelperImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val servicesModule = Kodein.Module("Services") {

	//db
	bind<DatabaseProvider>() with singleton { DatabaseProviderImpl(instance()) }
	bind<CityDao>() with provider { instance<DatabaseProvider>().db.cityDao() }
	bind<WeatherDao>() with provider { instance<DatabaseProvider>().db.weatherDao() }

	//api
	bind<NetworkService>() with singleton { NetworkServiceImpl() }
	bind<CityApi>() with singleton { instance<NetworkService>().cityApi }
	bind<WeatherApi>() with singleton { instance<NetworkService>().weatherApi }

	bind<PreferencesHelper>() with singleton { PreferencesHelperImpl(instance()) }
}