package by.olegkopeykin.weather.ui.screens.citydetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import by.olegkopeykin.model.domain.WeatherModel
import by.olegkopeykin.weather.R
import by.olegkopeykin.weather.common.BaseMvvmFragment
import by.olegkopeykin.weather.common.viewModelLazyFactory
import by.olegkopeykin.weather.databinding.FragmentCityDetailsBinding
import by.olegkopeykin.weather.ui.screens.citydetails.adapter.CityDetailsAdapter

class CityDetailsFragment : BaseMvvmFragment<CityDetailsViewModel, CityDetailsRouter>() {

    override val viewModel: CityDetailsViewModel by viewModelLazyFactory{
        arguments?.getParcelable(WEATHER_MODEL)?: WeatherModel.NONE
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentCityDetailsBinding>(
            inflater,
            R.layout.fragment_city_details,
            container,
            false
        ).also {
            it.viewModel = viewModel
            it.adapter = CityDetailsAdapter()
        }
        return binding.root
    }

    companion object{
        const val WEATHER_MODEL = "CITY_MODEL"
    }
}