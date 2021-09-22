package by.olegkopeykin.weather.ui.screens.selectcity.adapterlist

import by.olegkopeykin.model.domain.CityModel

interface CityListener {

    fun onCityClick(city: CityModel)
    fun onFavoriteClick(city: CityModel)
    fun onDeleteClick(city: CityModel)
}