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
	private val interactorCity: CityInteractor
) :
	BaseMvvmViewModel<CityDetailsRouter>(router) {

	val isFavorite = ObservableBoolean(cityModel.isFavorite)
	val listWeather = ObservableField<List<WeatherModel>>(listOf())

	init {
		updateData()
	}

	private fun updateData() = viewModelScope.launch {
		try {
			weatherInteractor.getCityWeatherOn7Days(cityModel)
		} catch (e: Throwable) {
			e.printStackTrace()
		}
	}

	fun onFavoriteClick() = viewModelScope.launch {
		try {
			val isFavoriteValue = isFavorite.get()
			interactorCity.setFavoriteCity(cityModel.copy(isFavorite = isFavoriteValue))
			isFavorite.set(!isFavoriteValue)
		} catch (e: Throwable) {
			e.printStackTrace()
		}
	}

	fun onDeleteClick() = viewModelScope.launch {
		try {
			interactorCity.removeCityDB(cityModel)
			router.onBackClick()
		} catch (e: Throwable) {
			e.printStackTrace()
		}
	}
}