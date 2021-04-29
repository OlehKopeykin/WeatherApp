package by.olegkopeykin.weather.screens

import by.olegkopeykin.model.domain.CityModel
import kotlin.random.Random

sealed class Screens {
	data class PrevScreen(val rnd: Int = Random.nextInt()) : Screens()
	object HideKeyboard : Screens()
	object CityList : Screens()
	class CityDetails(val city: CityModel) : Screens()
	object SelectCity : Screens()
}