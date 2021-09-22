package by.olegkopeykin.weather.ui.screens.citylist

import by.olegkopeykin.model.domain.WeatherModel
import by.olegkopeykin.weather.common.MvvmRouter

interface CityListRouter : MvvmRouter {
    fun showSelectCity()
    fun showCityDetails(weather: WeatherModel)
}