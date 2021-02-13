package by.olegkopeykin.services.preferences

import io.reactivex.Observable


interface PreferencesHelper {

    fun isFirstInit(): Boolean
    fun setFirstInit()

    val isLightMode: Observable<Boolean>
    fun changeColorMode()
}