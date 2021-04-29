package by.olegkopeykin.weather.app

import by.olegkopeykin.model.domain.CityModel
import by.olegkopeykin.weather.screens.MainViewModel
import by.olegkopeykin.weather.screens.citydetails.CityDetailsViewModel
import by.olegkopeykin.weather.screens.citylist.CityListViewModel
import by.olegkopeykin.weather.screens.selectcity.SelectCityViewModel
import by.olegkopeykin.weather.screens.splash.SplashViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.kodein.di.Kodein
import org.kodein.di.generic.*
import kotlin.time.ExperimentalTime

@FlowPreview
@ExperimentalCoroutinesApi
@ExperimentalTime
val appModule = Kodein.Module("App Weather") {

    bind<MainViewModel>() with singleton { MainViewModel(instance()) }
    bind<SplashViewModel>() with provider { SplashViewModel(instance(), instance(), instance()) }

    bind<SelectCityViewModel>() with provider { SelectCityViewModel(instance(), instance()) }
    bind<CityListViewModel>() with provider { CityListViewModel(instance(), instance(), instance(), instance()) }
    bind<CityDetailsViewModel>() with factory { cityModel: CityModel -> CityDetailsViewModel(instance(), cityModel, instance(), instance()) }
}