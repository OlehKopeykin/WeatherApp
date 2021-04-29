package by.olegkopeykin.interactors.pref

import kotlinx.coroutines.flow.Flow

interface PrefInteractor {
	fun isFirstInit(): Boolean
	fun setFirstInit()

	fun isLightModeStateFlow(): Flow<Boolean>
	fun changeLightMode()
}