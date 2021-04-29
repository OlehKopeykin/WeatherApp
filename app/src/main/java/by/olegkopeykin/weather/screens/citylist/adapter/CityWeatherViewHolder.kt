package by.olegkopeykin.weather.screens.citylist.adapter

import androidx.recyclerview.widget.RecyclerView
import by.olegkopeykin.model.domain.WeatherModel
import by.olegkopeykin.weather.databinding.ListitemCityWeatherBinding

class CityWeatherViewHolder(
    private val binding: ListitemCityWeatherBinding,
    private val listener: CityWeatherListener
) : RecyclerView.ViewHolder(binding.root) {

	fun bind(weatherModel: WeatherModel) {
		binding.item = weatherModel
		binding.temperature = weatherModel.temp.toString()
		binding.humidity = weatherModel.humidity.toString()
		binding.cloudy = weatherModel.clouds.toString()
		binding.windy = weatherModel.windSpeed.toString()
		binding.name = "${weatherModel.city.name}, ${weatherModel.city.country}"
		binding.listener = listener
		binding.executePendingBindings()
	}
}