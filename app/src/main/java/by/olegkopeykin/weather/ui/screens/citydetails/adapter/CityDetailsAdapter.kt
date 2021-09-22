package by.olegkopeykin.weather.ui.screens.citydetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import by.olegkopeykin.model.domain.WeatherModel
import by.olegkopeykin.weather.R
import by.olegkopeykin.weather.databinding.ListitemCityDefailsBinding

class CityDetailsAdapter: ListAdapter<WeatherModel, CityDetailsViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityDetailsViewHolder {
        val binding = DataBindingUtil.inflate<ListitemCityDefailsBinding>(
            LayoutInflater.from(parent.context),
            R.layout.listitem_city_defails,
            parent,
            false
        )
        return CityDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityDetailsViewHolder, position: Int) {
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