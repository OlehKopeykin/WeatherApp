package by.olegkopeykin.model.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CityModel(val name: String,
                     val lat : Double,
                     val lon : Double,
                     val country: String,
                     val isFavorite: Boolean = false):Parcelable{

    companion object{
        val NONE = CityModel("", 0.0, 0.0, "")
    }
}