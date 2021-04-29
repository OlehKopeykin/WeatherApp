package by.olegkopeykin.model.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherModel(
	val city: CityModel,

	val weatherTitle: String,
	val weatherDescription: String,

	val temp: Double = 0.0,
	val minTemp: Double = 0.0,
	val maxTemp: Double = 0.0,

	val feelLike: Double = 0.0,

	val humidity: Int,
	val clouds: Int,
	val windDeg: Int,
	val windSpeed: Double,
	val date: Long
) : Parcelable {

	companion object {
		val NONE = WeatherModel(
			city = CityModel.NONE,
			weatherTitle = "",
			weatherDescription = "",
			temp = 0.0,
			minTemp = 0.0,
			maxTemp = 0.0,
			feelLike = 0.0,
			humidity = 0,
			clouds = 0,
			windDeg = 0,
			windSpeed = 0.0,
			date = 0L
		)
	}
}