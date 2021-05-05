package by.olegkopeykin.weather.screens

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.viewModelScope
import by.olegkopeykin.interactors.pref.PrefInteractor
import by.olegkopeykin.model.domain.CityModel
import by.olegkopeykin.weather.common.BaseViewModel
import by.olegkopeykin.weather.screens.citydetails.CityDetailsRouter
import by.olegkopeykin.weather.screens.citylist.CityListRouter
import by.olegkopeykin.weather.screens.selectcity.SelectCityRouter
import by.olegkopeykin.weather.screens.splash.SplashRouter
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(prefInteractor: PrefInteractor) : BaseViewModel(),
	SplashRouter,
	CityListRouter,
	CityDetailsRouter,
	SelectCityRouter {

	val nextScreen: Flow<Screens> get() = postNextScreen.asSharedFlow()
	private val postNextScreen: MutableSharedFlow<Screens> = MutableSharedFlow(
		replay = 1,
		onBufferOverflow = BufferOverflow.DROP_LATEST
	)

	init {
		prefInteractor.isLightModeStateFlow()
			.onEach {
				if (it) {
					AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
				} else {
					AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
				}
			}
			.catch { it.printStackTrace() }
			.launchIn(viewModelScope)
	}

	override fun showCityList() {
		emitNextScreen(Screens.CityList)
	}

	override fun showSelectCity() {
		emitNextScreen(Screens.SelectCity)
	}

	override fun showCityDetails(city: CityModel) {
		emitNextScreen(Screens.CityDetails(city))
	}

	override fun onBackClick() {
		emitNextScreen(Screens.PrevScreen())
	}

	override fun hideKeyboard() {
		emitNextScreen(Screens.HideKeyboard)
	}

	private fun emitNextScreen(screen: Screens) {
		viewModelScope.launch { postNextScreen.emit(screen) }
	}
}