package by.olegkopeykin.weather.screens.citydetails.adapter

import androidx.recyclerview.widget.RecyclerView
import by.olegkopeykin.model.domain.WeatherModel
import by.olegkopeykin.weather.databinding.ListitemCityDefailsBinding
import by.olegkopeykin.weather.utils.DateHelper

class CityDetailsViewHolder(
    private val binding: ListitemCityDefailsBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(weatherModel: WeatherModel) {
        binding.temperature = weatherModel.temp.toString()
        binding.humidity = weatherModel.humidity.toString()
        binding.cloudy = weatherModel.clouds.toString()
        binding.windy = weatherModel.windSpeed.toString()
        binding.item = weatherModel
        binding.dateWeather = DateHelper.getDateWeather(weatherModel.date)
        binding.executePendingBindings()
    }
}