package by.olegkopeykin.weather.screens.selectcity.adaptersearch

import by.olegkopeykin.model.domain.CityModel

interface SearchCityListener {

    fun onCityClick(city: CityModel)
}