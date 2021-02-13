package by.olegkopeykin.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CityEntity(@PrimaryKey(autoGenerate = true) val id:Long = 0L,
                      val name:String,
                      val countryCode:String,
                      val lat:Double,
                      val lon:Double,
                      val isFavoriteCity: Boolean = false)