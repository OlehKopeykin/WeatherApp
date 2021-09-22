package by.olegkopeykin.weather.ui.screens.selectcity.adaptersearch

import by.olegkopeykin.model.domain.CityModel

interface SearchCityListener {

    fun onCityClick(city: CityModel)
}