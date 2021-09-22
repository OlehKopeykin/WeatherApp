package by.olegkopeykin.weather.ui.screens.citylist.adapter

import by.olegkopeykin.model.domain.WeatherModel

interface CityWeatherListener {

    fun onCityClick(city: WeatherModel)
}