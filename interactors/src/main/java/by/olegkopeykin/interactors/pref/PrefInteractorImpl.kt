package by.olegkopeykin.interactors.pref

import by.olegkopeykin.services.preferences.PreferencesHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PrefInteractorImpl(private val prefService: PreferencesHelper) : PrefInteractor {

	private val isLightModeState = MutableStateFlow(prefService.isLightMode)

	override fun isFirstInit(): Boolean {
		return prefService.isFirstInit
	}

	override fun setFirstInit() {
		prefService.isFirstInit = false
	}

	override fun isLightModeStateFlow(): Flow<Boolean> {
		return isLightModeState.asStateFlow()
	}

	override fun changeLightMode() {
		val mode = !prefService.isLightMode
		isLightModeState.value = mode
		prefService.isLightMode = mode
	}
}