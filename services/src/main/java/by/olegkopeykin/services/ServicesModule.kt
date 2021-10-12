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
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [ServiceModuleBind::class])
class ServicesModule {
    // db
    @Provides
    fun provideCityDao(dbProvider: DatabaseProvider): CityDao = dbProvider.db.cityDao()
    @Provides
    fun provideWeatherDao(dbProvider: DatabaseProvider): WeatherDao = dbProvider.db.weatherDao()

    // api
    @Provides
    fun getCityApi(networkService: NetworkService): CityApi = networkService.cityApi
    @Provides
    fun getWeatherApi(networkService: NetworkService): WeatherApi = networkService.weatherApi
}

@Module
private interface ServiceModuleBind {
    // db
    @Binds
    fun bindDatabaseProvider(databaseProviderImpl: DatabaseProviderImpl): DatabaseProvider

    // api
    @Binds
    fun bindNetworkService(networkServiceImpl: NetworkServiceImpl): NetworkService

    // preference
    @Binds
    fun bindPreferenceHelper(preferencesHelperImpl: PreferencesHelperImpl): PreferencesHelper
}