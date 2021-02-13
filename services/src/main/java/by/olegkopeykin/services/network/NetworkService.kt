package by.olegkopeykin.services.network

import by.olegkopeykin.services.network.api.CityApi
import by.olegkopeykin.services.network.api.WeatherApi

interface NetworkService {

    val weatherApi: WeatherApi
    val cityApi: CityApi
}