package by.olegkopeykin.weather.ui.screens.selectcity.adapterlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import by.olegkopeykin.model.domain.CityModel
import by.olegkopeykin.weather.R
import by.olegkopeykin.weather.databinding.ListitemCityBinding

class CityAdapter(private val listener: CityListener) :
    ListAdapter<CityModel, CityViewHolder>(CityAdapter.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val binding = DataBindingUtil.inflate<ListitemCityBinding>(
            LayoutInflater.from(parent.context),
            R.layout.listitem_city,
            parent,
            false
        )
        return CityViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
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