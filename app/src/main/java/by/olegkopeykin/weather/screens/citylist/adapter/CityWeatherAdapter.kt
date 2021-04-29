package by.olegkopeykin.weather.screens.citylist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import by.olegkopeykin.model.domain.WeatherModel
import by.olegkopeykin.weather.R
import by.olegkopeykin.weather.databinding.ListitemCityWeatherBinding

class CityWeatherAdapter(private val listener: CityWeatherListener) :
	ListAdapter<WeatherModel, CityWeatherViewHolder>(DIFF_CALLBACK) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityWeatherViewHolder {
		val binding = DataBindingUtil.inflate<ListitemCityWeatherBinding>(
            LayoutInflater.from(parent.context),
            R.layout.listitem_city_weather,
            parent,
            false
        )
		return CityWeatherViewHolder(binding, listener)
	}

	override fun onBindViewHolder(holder: CityWeatherViewHolder, position: Int) {
		holder.bind(getItem(position))
	}

	companion object {
		private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WeatherModel>() {
			override fun areItemsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
				return oldItem == newItem
			}

			override fun areContentsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
				return oldItem == newItem
			}
		}
	}
}