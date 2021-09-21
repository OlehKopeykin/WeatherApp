package by.olegkopeykin.model.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherModel(
	val nameCity: String,
	val country: String,
	val lat: Double,
	val lon: Double,
	val isFavorite: Boolean = false,

	val weatherTitle: String,
	val weatherDescription: String,

	val temp: Double = 0.0,
	val minTemp: Double = 0.0,
	val maxTemp: Double = 0.0,

	val feelLike: Double = 0.0,

	val humidity: Int, //влажность
	val clouds: Int,
	val windDeg: Int,
	val windSpeed: Double,
	val date: Long
) : Parcelable {

	companion object {
		val NONE =
			WeatherModel("", "", 0.0, 0.0, false, "", "", 0.0, 0.0, 0.0, 0.0, 0, 0, 0, 0.0, 0L)
	}
}