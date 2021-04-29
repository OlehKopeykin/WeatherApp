package by.olegkopeykin.model.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CityModel(
	val name: String,
	val lat: Double,
	val lon: Double,
	val country: String,
	val isFavorite: Boolean = false
) : Parcelable {

	companion object {
		val NONE = CityModel(
			name = "",
			lat = 0.0,
			lon = 0.0,
			country = ""
		)
	}
}