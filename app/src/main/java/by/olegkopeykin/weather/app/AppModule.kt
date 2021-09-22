package by.olegkopeykin.weather.app

import by.olegkopeykin.model.domain.WeatherModel
import by.olegkopeykin.weather.ui.screens.MainViewModel
import by.olegkopeykin.weather.ui.screens.citydetails.CityDetailsViewModel
import by.olegkopeykin.weather.ui.screens.citylist.CityListViewModel
import by.olegkopeykin.weather.ui.screens.selectcity.SelectCityViewModel
import by.olegkopeykin.weather.ui.screens.splash.SplashViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.*

val appModule = Kodein.Module("App Weather") {

    bind<MainViewModel>() with singleton { MainViewModel(instance()) }
    bind<SplashViewModel>() with provider { SplashViewModel(instance(), instance(), instance()) }

    bind<SelectCityViewModel>() with provider { SelectCityViewModel(instance(), instance(), instance()) }
    bind<CityListViewModel>() with provider { CityListViewModel(instance(), instance(), instance(), instance()) }
    bind<CityDetailsViewModel>() with factory { weatherModel: WeatherModel -> CityDetailsViewModel(instance(), weatherModel, instance(), instance()) }
}