package by.olegkopeykin.services.preferences

import android.content.Context
import android.content.SharedPreferences
import by.olegkopeykin.services.preferences.common.BooleanPreferenceDelegate

class PreferencesHelperImpl(context: Context) : PreferencesHelper {

	private val preferences: SharedPreferences = context.getSharedPreferences(NAME_PREFS, Context.MODE_PRIVATE)

	override var isFirstInit: Boolean by BooleanPreferenceDelegate(preferences, IS_FIRST_INIT, true)
	override var isLightMode: Boolean by BooleanPreferenceDelegate(preferences, IS_LIGHT_MODE, false)

	companion object {
		private const val NAME_PREFS = "APP_WEATHER"
		private const val IS_FIRST_INIT = "IS_FIRST_INIT"
		private const val IS_LIGHT_MODE = "IS_LIGHT_MODE"
	}
}