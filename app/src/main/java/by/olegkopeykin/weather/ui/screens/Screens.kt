package by.olegkopeykin.weather.ui.screens

import by.olegkopeykin.model.domain.WeatherModel
import kotlin.random.Random

sealed class Screens {

    data class PrevScreen(val rnd: Int = Random.nextInt()) : Screens()
    object HideKeyboard:Screens()
    object CityList : Screens()
    class CityDetails(val weather:WeatherModel) : Screens()
    object SelectCity : Screens()
}