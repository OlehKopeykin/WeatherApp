package by.olegkopeykin.weather.ui.screens.citydetails

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.olegkopeykin.interactors.cities.CityInteractor
import by.olegkopeykin.interactors.weather.WeatherInteractor
import by.olegkopeykin.model.domain.CityModel
import by.olegkopeykin.model.domain.WeatherModel
import by.olegkopeykin.weather.common.BaseMvvmViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.android.schedulers.AndroidSchedulers

class CityDetailsViewModel(
    router: CityDetailsRouter,
    val weatherModel: WeatherModel,
    weatherInteractor: WeatherInteractor,
    private val interactorCity: CityInteractor
) : BaseMvvmViewModel<CityDetailsRouter>(router) {

    val isFavorite = ObservableBoolean(weatherModel.isFavorite)
    val listWeather = ObservableField<List<WeatherModel>>(listOf())

    init {
        weatherInteractor.getCityWeatherOn7Days(
            CityModel(
                weatherModel.nameCity,
                weatherModel.lat,
                weatherModel.lon,
                weatherModel.country,
                weatherModel.isFavorite
            )
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listWeather.set(it)
            }, {
                it.printStackTrace()
            })
            .toComposite()
    }

    fun onFavoriteClick() {
        val isFavoriteValue = isFavorite.get()
        interactorCity.setFavoriteCity(
            CityModel(
                weatherModel.nameCity,
                weatherModel.lat,
                weatherModel.lon,
                weatherModel.country,
                isFavoriteValue
            )
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                isFavorite.set(!isFavoriteValue)
            }, {
                it.printStackTrace()
            }).toComposite()
    }

    fun onDeleteClick() {
        interactorCity.removeCityDB(
            CityModel(
                weatherModel.nameCity,
                weatherModel.lat,
                weatherModel.lon,
                weatherModel.country,
                isFavorite.get()
            )
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                router.onBackClick()
            }, {
                it.printStackTrace()
            }).toComposite()
    }

    class Factory @AssistedInject constructor(
        private val router: CityDetailsRouter,
        @Assisted private val weatherModel: WeatherModel,
        private val weatherInteractor: WeatherInteractor,
        private val interactorCity: CityInteractor
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CityDetailsViewModel(
                router,
                weatherModel,
                weatherInteractor,
                interactorCity
            ) as T
        }

        @AssistedFactory
        interface DaggerFactory {
            fun create(weatherModel: WeatherModel): Factory
        }
    }
}