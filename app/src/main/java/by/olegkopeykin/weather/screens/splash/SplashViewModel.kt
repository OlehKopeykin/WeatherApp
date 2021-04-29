package by.olegkopeykin.weather.screens.splash

import android.text.format.DateUtils
import androidx.lifecycle.viewModelScope
import by.olegkopeykin.interactors.cities.CityInteractor
import by.olegkopeykin.interactors.pref.PrefInteractor
import by.olegkopeykin.weather.common.BaseMvvmViewModel
import kotlinx.coroutines.*

class SplashViewModel(
	router: SplashRouter,
	interactorPref: PrefInteractor,
	interactorCity: CityInteractor
) : BaseMvvmViewModel<SplashRouter>(router) {

	private val addedCitiesJob: Job by lazy {
		viewModelScope.launch {
			if (interactorPref.isFirstInit()) {
				try {
					interactorCity.addDefaultCities()
				} catch (e: Throwable) {
					e.printStackTrace()
				}
			}
		}
	}

	init {
		addedCitiesJob.start()
	}

	fun doRouting() = viewModelScope.launch {
		try {
			listOf(addedCitiesJob, launch { delay(LAUNCH_TIMER_MS) }).joinAll()
			routingScreens()
		} catch (e: Throwable) {
			e.printStackTrace()
		}
	}

	private fun routingScreens() {
		router.showCityList()
	}

	companion object {
		private const val LAUNCH_TIMER_MS = 2L * DateUtils.SECOND_IN_MILLIS
	}
}