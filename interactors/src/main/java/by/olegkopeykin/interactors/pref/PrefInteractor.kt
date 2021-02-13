package by.olegkopeykin.interactors.pref

import io.reactivex.Observable

interface PrefInteractor {

    fun isFirstInit(): Boolean
    fun setFirstInit()

    fun isLightMode(): Observable<Boolean>
    fun changeColorMode()
}