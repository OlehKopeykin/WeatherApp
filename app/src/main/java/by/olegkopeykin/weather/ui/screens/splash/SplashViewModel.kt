package by.olegkopeykin.weather.ui.screens.splash

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.olegkopeykin.interactors.cities.CityInteractor
import by.olegkopeykin.interactors.pref.PrefInteractor
import by.olegkopeykin.weather.common.BaseMvvmViewModel
import by.olegkopeykin.weather.common.toObservable
import by.olegkopeykin.weather.ui.screens.selectcity.SelectCityRouter
import by.olegkopeykin.weather.ui.screens.selectcity.SelectCityViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.Observables
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel(
    router: SplashRouter,
    interactorPref: PrefInteractor,
    interactorCity: CityInteractor
) : BaseMvvmViewModel<SplashRouter>(router) {

    private val isLoading = ObservableBoolean(false)
    private val isAddedCities = ObservableBoolean(false)
    private val isFirstInit = interactorPref.isFirstInit()

    init {
        if (isFirstInit) {
            addDisposable(
                interactorCity.addDefaultCities()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        isAddedCities.set(true)
                    }, {
                        isAddedCities.set(true)
                        it.printStackTrace()
                    })
            )
        } else {
            isAddedCities.set(true)
        }
    }

    fun doRouting() {
        isLoading.set(true)
        addDisposable(
            Observables.combineLatest(
                isAddedCities.toObservable().filter { it },
                Observable.timer(LAUNCH_TIMER, TimeUnit.MILLISECONDS)
            )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    routingScreens()
                    isLoading.set(false)
                }, {
                    it.printStackTrace()
                })
        )
    }

    private fun routingScreens() {
        router.showCityList()
    }

    class Factory @Inject constructor(
        private val router: SplashRouter,
        private val interactorPref: PrefInteractor,
        private val interactorCity: CityInteractor
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SplashViewModel(router, interactorPref, interactorCity) as T
        }
    }

    companion object {
        private const val LAUNCH_TIMER = 2000L
    }
}