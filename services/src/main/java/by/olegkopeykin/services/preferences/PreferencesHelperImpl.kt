package by.olegkopeykin.services.preferences

import android.content.Context
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable

class PreferencesHelperImpl(private val context: Context) : PreferencesHelper{

    private val preferences = context.getSharedPreferences(NAME_PREFS, Context.MODE_PRIVATE)

    private val colorMode = BehaviorRelay.createDefault(isLightMode())
    override val isLightMode: Observable<Boolean>
        get() = colorMode

    override fun isFirstInit(): Boolean {
        return preferences.getBoolean(IS_FIRST_INIT, true)
    }

    override fun setFirstInit() {
        preferences.edit().putBoolean(IS_FIRST_INIT, false).apply()
    }

    private fun isLightMode(): Boolean {
        return preferences.getBoolean(IS_LIGHT_MODE, false)
    }

    override fun changeColorMode() {
        val mode = !isLightMode()
        colorMode.accept(mode)
        preferences.edit().putBoolean(IS_LIGHT_MODE, mode).apply()
    }

    companion object {
        private const val NAME_PREFS = "APP_WEATHER"
        private const val IS_FIRST_INIT = "IS_FIRST_INIT"
        private const val IS_LIGHT_MODE = "IS_LIGHT_MODE"
    }
}