package by.olegkopeykin.model.network.weather.onweek

import com.google.gson.annotations.SerializedName

data class Snow(
    @SerializedName("1h")
    val value_1h:Double
)