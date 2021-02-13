package by.olegkopeykin.weather.screens.selectcity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import by.olegkopeykin.weather.R
import by.olegkopeykin.weather.common.BaseMvvmFragment
import by.olegkopeykin.weather.common.viewModelLazyInstance
import by.olegkopeykin.weather.databinding.FragmentCitySelectBinding
import by.olegkopeykin.weather.screens.selectcity.adapterlist.CityAdapter
import by.olegkopeykin.weather.screens.selectcity.adaptersearch.SearchCityAdapter

class SelectCityFragment : BaseMvvmFragment<SelectCityViewModel, SelectCityRouter>() {

    override val viewModel: SelectCityViewModel by viewModelLazyInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentCitySelectBinding>(
            inflater,
            R.layout.fragment_city_select,
            container,
            false
        ).also { binding->
            binding.viewModel = viewModel
            binding.adapterSearchCity = SearchCityAdapter(viewModel.searchCityListener)
            binding.adapterCity = CityAdapter(viewModel.cityListener)
        }
        return binding.root
    }
}