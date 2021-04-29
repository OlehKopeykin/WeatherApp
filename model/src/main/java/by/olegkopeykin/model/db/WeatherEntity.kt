package by.olegkopeykin.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
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

    val humidity: Int,
    val clouds: Int,
    val windDeg: Int,
    val windSpeed: Double,
    val date: Long
)