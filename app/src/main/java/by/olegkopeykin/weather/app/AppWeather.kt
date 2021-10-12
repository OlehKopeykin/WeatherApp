package by.olegkopeykin.weather.app

import androidx.multidex.MultiDexApplication

class AppWeather : MultiDexApplication() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}