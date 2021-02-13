package by.olegkopeykin.weather.screens.selectcity.adaptersearch

import androidx.recyclerview.widget.RecyclerView
import by.olegkopeykin.model.domain.CityModel
import by.olegkopeykin.weather.databinding.ListitemCitySearchBinding

class SearchCityViewHolder(
    private val binding: ListitemCitySearchBinding,
    private val listener: SearchCityListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(city: CityModel) {
        binding.item = city
        binding.name = "${city.name}, ${city.country}"
        binding.listener = listener
        binding.executePendingBindings()
    }
}