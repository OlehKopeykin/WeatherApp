package by.olegkopeykin.weather.ui.screens.selectcity.adapterlist

import androidx.recyclerview.widget.RecyclerView
import by.olegkopeykin.model.domain.CityModel
import by.olegkopeykin.weather.databinding.ListitemCityBinding

class CityViewHolder(
    private val binding: ListitemCityBinding,
    private val listener: CityListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(city: CityModel) {
        binding.item = city
        binding.name = "${city.name}, ${city.country}"
        binding.listener = listener
        binding.executePendingBindings()
    }
}
