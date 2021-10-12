package by.olegkopeykin.weather.app

import android.content.Context
import by.olegkopeykin.interactors.InteractorModule
import by.olegkopeykin.model.ModelsModule
import by.olegkopeykin.services.ServicesModule
import by.olegkopeykin.weather.ui.screens.MainActivity
import by.olegkopeykin.weather.ui.screens.MainViewModel
import by.olegkopeykin.weather.ui.screens.citydetails.CityDetailsFragment
import by.olegkopeykin.weather.ui.screens.citydetails.CityDetailsRouter
import by.olegkopeykin.weather.ui.screens.citylist.CityListFragment
import by.olegkopeykin.weather.ui.screens.citylist.CityListRouter
import by.olegkopeykin.weather.ui.screens.selectcity.SelectCityFragment
import by.olegkopeykin.weather.ui.screens.selectcity.SelectCityRouter
import by.olegkopeykin.weather.ui.screens.splash.SplashFragment
import by.olegkopeykin.weather.ui.screens.splash.SplashRouter
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        InteractorModule::class,
        ServicesModule::class,
        ModelsModule::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(cityDetailsFragment: CityDetailsFragment)
    fun inject(cityListFragment: CityListFragment)
    fun inject(selectCityFragment: SelectCityFragment)
    fun inject(splashFragment: SplashFragment)
}

@Module
interface AppModule {
    @Binds
    fun bindCityDetailsRouter(mainViewModel: MainViewModel): CityDetailsRouter
    @Binds
    fun bindCityListRouter(mainViewModel: MainViewModel): CityListRouter
    @Binds
    fun bindSelectCityRouter(mainViewModel: MainViewModel): SelectCityRouter
    @Binds
    fun bindSplashRouter(mainViewModel: MainViewModel): SplashRouter
}

fun Context.appComponent(): AppComponent {
    return when(this) {
        is AppWeather -> this.appComponent
        else -> (applicationContext as AppWeather).appComponent
    }
}