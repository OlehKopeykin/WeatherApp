package by.olegkopeykin.weather.ui.screens.selectcity

import by.olegkopeykin.weather.common.MvvmRouter

interface SelectCityRouter : MvvmRouter {
    fun onBackClick()
    fun hideKeyboard()
}