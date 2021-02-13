package by.olegkopeykin.interactors.pref

import by.olegkopeykin.services.preferences.PreferencesHelper
import io.reactivex.Observable

class PrefInteractorImpl(private val prefService: PreferencesHelper): PrefInteractor{

    override fun isFirstInit(): Boolean {
        return prefService.isFirstInit()
    }

    override fun setFirstInit() {
       prefService.setFirstInit()
    }

    override fun isLightMode(): Observable<Boolean> {
       return prefService.isLightMode
    }

    override fun changeColorMode() {
        return prefService.changeColorMode()
    }
}