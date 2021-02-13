package by.olegkopeykin.weather.screens.citylist

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import by.olegkopeykin.interactors.cities.CityInteractor
import by.olegkopeykin.interactors.pref.PrefInteractor
import by.olegkopeykin.interactors.weather.WeatherInteractor
import by.olegkopeykin.model.domain.CityModel
import by.olegkopeykin.model.domain.WeatherModel
import by.olegkopeykin.weather.common.BaseMvvmViewModel
import by.olegkopeykin.weather.common.toObservable
import by.olegkopeykin.weather.screens.citylist.adapter.CityWeatherListener
import io.reactivex.android.schedulers.AndroidSchedulers

class CityListViewModel(router: CityListRouter,
                        private val cityInteractor: CityInteractor,
                        private val prefInteractor: PrefInteractor,
                        private val weatherInteractor: WeatherInteractor) : BaseMvvmViewModel<CityListRouter>(router) {

    private val listCities = ObservableField<List<CityModel>>(listOf())
    val listWeatherModels = ObservableField<List<WeatherModel>>(listOf())
    val isLightColorMode = ObservableBoolean(false)

    val listener = object : CityWeatherListener {
        override fun onCityClick(city: WeatherModel) {
            router.showCityDetails(city)
        }
    }
    init {
        cityInteractor.getCities()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listCities.set(it)
            },{
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

        listCities.toObservable()
            .switchMapSingle {
                weatherInteractor.getWeatherNowForCities(it)
            }
            .map {
                it.sortedBy { !it.isFavorite }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listWeatherModels.set(it)
            },{
                it.printStackTrace()
            }).toComposite()
    }

    fun onChangeColorModeClick(){
        prefInteractor.changeColorMode()
    }

    fun onEditClick(){
        router.showSelectCity()
    }
}