package by.olegkopeykin.weather.ui.screens.selectcity.adaptersearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import by.olegkopeykin.model.domain.CityModel
import by.olegkopeykin.weather.R
import by.olegkopeykin.weather.databinding.ListitemCitySearchBinding

class SearchCityAdapter(private val listener: SearchCityListener) : ListAdapter<CityModel, SearchCityViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchCityViewHolder {
        val binding = DataBindingUtil.inflate<ListitemCitySearchBinding>(
            LayoutInflater.from(parent.context),
            R.layout.listitem_city_search,
            parent,
            false
        )
        return SearchCityViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: SearchCityViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CityModel>() {
            override fun areItemsTheSame(oldItem: CityModel, newItem: CityModel): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: CityModel, newItem: CityModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}