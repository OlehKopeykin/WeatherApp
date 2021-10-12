package by.olegkopeykin.weather.ui.screens.citylist

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.olegkopeykin.interactors.cities.CityInteractor
import by.olegkopeykin.interactors.pref.PrefInteractor
import by.olegkopeykin.interactors.weather.WeatherInteractor
import by.olegkopeykin.model.domain.WeatherModel
import by.olegkopeykin.weather.common.BaseMvvmViewModel
import by.olegkopeykin.weather.ui.screens.citylist.adapter.CityWeatherListener
import by.olegkopeykin.weather.ui.screens.selectcity.SelectCityViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class CityListViewModel(
    router: CityListRouter,
    cityInteractor: CityInteractor,
    private val prefInteractor: PrefInteractor,
    private val weatherInteractor: WeatherInteractor
) : BaseMvvmViewModel<CityListRouter>(router) {

    val listWeatherModels = ObservableField<List<WeatherModel>>(listOf())
    val isLightColorMode = ObservableBoolean(false)

    val listener = object : CityWeatherListener {
        override fun onCityClick(city: WeatherModel) {
            router.showCityDetails(city)
        }
    }

    init {
        cityInteractor.getCities()
            .switchMapSingle {
                weatherInteractor.getWeatherNowForCities(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, {
                it.printStackTrace()
            }).toComposite()

        prefInteractor.isLightMode()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                isLightColorMode.set(it)
            }, {
                it.printStackTrace()
            })
            .toComposite()

        weatherInteractor.getWeatherNowCitiesFromDB()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listWeatherModels.set(it.sortedBy { it.nameCity }.sortedBy { !it.isFavorite })
            }, {
                it.printStackTrace()
            }).toComposite()
    }

    fun onChangeColorModeClick() {
        prefInteractor.changeColorMode()
    }

    fun onEditClick() {
        router.showSelectCity()
    }

    class Factory @Inject constructor(
        private val router: CityListRouter,
        private val cityInteractor: CityInteractor,
        private val prefInteractor: PrefInteractor,
        private val weatherInteractor: WeatherInteractor
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CityListViewModel(router, cityInteractor, prefInteractor, weatherInteractor) as T
        }
    }
}