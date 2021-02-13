package by.olegkopeykin.weather.app

import android.content.Context
import androidx.multidex.MultiDexApplication
import by.olegkopeykin.interactors.interactorsModule
import by.olegkopeykin.model.modelsModule
import by.olegkopeykin.services.servicesModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class AppWeather : MultiDexApplication(), KodeinAware {

    override val kodein by lazy {
        Kodein {
            bind<Context>() with singleton { this@AppWeather }
            import(appModule)
            import(modelsModule)
            import(servicesModule)
            import(interactorsModule)
            kodein(this)
        }
    }

    protected open fun kodein(mainBuilder: Kodein.MainBuilder) {

    }
}