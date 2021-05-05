package by.olegkopeykin.weather.screens.citydetails

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import by.olegkopeykin.interactors.cities.CityInteractor
import by.olegkopeykin.interactors.weather.WeatherInteractor
import by.olegkopeykin.model.domain.CityModel
import by.olegkopeykin.model.domain.WeatherModel
import by.olegkopeykin.weather.common.BaseMvvmViewModel
import kotlinx.coroutines.launch

class CityDetailsViewModel(
	router: CityDetailsRouter,
	val cityModel: CityModel,
	private val weatherInteractor: WeatherInteractor,
	private val cityInteractor: CityInteractor
) :
	BaseMvvmViewModel<CityDetailsRouter>(router) {

	val isFavorite = ObservableBoolean(cityModel.isFavorite)
	val listWeather = ObservableField<List<WeatherModel>>(listOf())

	init {
		updateData()
	}

	private fun updateData() = viewModelScope.launch {
		try {
			listWeather.set(weatherInteractor.getCityWeatherOn7Days(cityModel))
		} catch (e: Throwable) {
			e.printStackTrace()
		}
	}

	fun onFavoriteClick() = viewModelScope.launch {
		try {
			cityInteractor.setFavoriteCity(cityModel)
			isFavorite.set(!isFavorite.get())
		} catch (e: Throwable) {
			e.printStackTrace()
		}
	}

	fun onDeleteClick() = viewModelScope.launch {
		try {
			cityInteractor.removeCityDB(cityModel)
			router.onBackClick()
		} catch (e: Throwable) {
			e.printStackTrace()
		}
	}
}