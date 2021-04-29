package by.olegkopeykin.weather.screens.citylist

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import by.olegkopeykin.interactors.cities.CityInteractor
import by.olegkopeykin.interactors.pref.PrefInteractor
import by.olegkopeykin.interactors.weather.WeatherInteractor
import by.olegkopeykin.model.domain.WeatherModel
import by.olegkopeykin.weather.common.BaseMvvmViewModel
import by.olegkopeykin.weather.screens.citylist.adapter.CityWeatherListener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class CityListViewModel(
	router: CityListRouter,
	cityInteractor: CityInteractor,
	private val prefInteractor: PrefInteractor,
	private val weatherInteractor: WeatherInteractor
) : BaseMvvmViewModel<CityListRouter>(router) {

	val listWeatherModels = ObservableField<List<WeatherModel>>(listOf())
	val isLightColorMode = ObservableBoolean(false)

	val listener = object : CityWeatherListener {
		override fun onCityClick(weather: WeatherModel) {
			router.showCityDetails(weather.city)
		}
	}

	init {
		cityInteractor.getCitiesDB()
			.flatMapLatest { weatherInteractor.getWeatherNowForCities(it).asFlow() }
			.catch { it.printStackTrace() }
			.launchIn(viewModelScope)

		weatherInteractor.getWeatherNowCitiesFromDB()
			.onEach { listWeather ->
				listWeatherModels.set(listWeather.sortedBy { it.city.name }.sortedBy { !it.city.isFavorite })
			}
			.catch { it.printStackTrace() }
			.launchIn(viewModelScope)

		prefInteractor.isLightModeStateFlow()
			.onEach { isLightColorMode.set(it) }
			.catch { it.printStackTrace() }
			.launchIn(viewModelScope)
	}

	fun onChangeColorModeClick() = viewModelScope.launch {
		prefInteractor.changeLightMode()
	}

	fun onEditClick() {
		router.showSelectCity()
	}
}