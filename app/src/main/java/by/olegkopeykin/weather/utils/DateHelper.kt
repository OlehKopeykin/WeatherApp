package by.olegkopeykin.weather.utils

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    private val format = SimpleDateFormat("dd MMMM").apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }

    fun getDateWeather(date:Long):String{
        return format.format(Date(date*1000).time)
    }
}