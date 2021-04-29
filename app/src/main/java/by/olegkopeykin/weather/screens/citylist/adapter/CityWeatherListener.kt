package by.olegkopeykin.weather.screens.citylist.adapter

import by.olegkopeykin.model.domain.WeatherModel

interface CityWeatherListener {
	fun onCityClick(weather: WeatherModel)
}