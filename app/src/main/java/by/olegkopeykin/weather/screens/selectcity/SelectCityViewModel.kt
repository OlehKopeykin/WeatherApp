package by.olegkopeykin.weather.screens.selectcity

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import by.olegkopeykin.interactors.cities.CityInteractor
import by.olegkopeykin.model.domain.CityModel
import by.olegkopeykin.weather.common.BaseMvvmViewModel
import by.olegkopeykin.weather.common.toFlow
import by.olegkopeykin.weather.screens.selectcity.adapterlist.CityListener
import by.olegkopeykin.weather.screens.selectcity.adaptersearch.SearchCityListener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.time.ExperimentalTime
import kotlin.time.milliseconds

@ExperimentalCoroutinesApi
@FlowPreview
@ExperimentalTime
class SelectCityViewModel(
	router: SelectCityRouter,
	private val cityInteractor: CityInteractor,
) : BaseMvvmViewModel<SelectCityRouter>(router) {

	val listSearch = ObservableField<List<CityModel>>(listOf())
	val listCities = ObservableField<List<CityModel>>(listOf())

	val inputSearch = ObservableField("")

	val searchCityListener = object : SearchCityListener {
		override fun onCityClick(city: CityModel) {
			router.hideKeyboard()
			inputSearch.set("")
			viewModelScope.launch {
				try {
					cityInteractor.saveCityDB(city)
				} catch (e: Throwable) {
					e.printStackTrace()
				}
			}
		}
	}

	val cityListener = object : CityListener {
		override fun onCityClick(city: CityModel) {

		}

		override fun onFavoriteClick(city: CityModel) {
			viewModelScope.launch {
				try {
					cityInteractor.setFavoriteCity(city)
				} catch (e: Throwable) {
					e.printStackTrace()
				}
			}
		}

		override fun onDeleteClick(city: CityModel) {
			viewModelScope.launch {
				try {
					cityInteractor.removeCityDB(city)
				} catch (e: Throwable) {
					e.printStackTrace()
				}
			}
		}
	}

	init {
		inputSearch.toFlow()
			.debounce(500.milliseconds)
			.onEach { cityName ->
				try {
					cityInteractor.getCityByName(cityName).also { listSearch.set(it) }
				} catch (e: Throwable) {
					e.printStackTrace()
				}
			}
			.catch { it.printStackTrace() }
			.launchIn(viewModelScope)

		cityInteractor.getCitiesDB()
			.onEach { listCities.set(it) }
			.catch { it.printStackTrace() }
			.launchIn(viewModelScope)
	}
}