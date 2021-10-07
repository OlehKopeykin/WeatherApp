package by.olegkopeykin.weather.app

import android.content.Context
import androidx.multidex.MultiDexApplication
import by.olegkopeykin.interactors.interactorsModule
import by.olegkopeykin.model.modelsModule
import by.olegkopeykin.services.servicesModule
import org.kodein.di.*

class AppWeather : MultiDexApplication(), DIAware {

    override val di by lazy {
        DI {
            bind<Context>() with singleton { this@AppWeather }
            import(appModule)
            import(modelsModule)
            import(servicesModule)
            import(interactorsModule)
        }
    }
}