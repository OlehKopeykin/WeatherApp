package by.olegkopeykin.weather.ui.screens.selectcity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import by.olegkopeykin.weather.R
import by.olegkopeykin.weather.app.appComponent
import by.olegkopeykin.weather.common.BaseMvvmFragment
import by.olegkopeykin.weather.databinding.FragmentCitySelectBinding
import by.olegkopeykin.weather.ui.screens.selectcity.adapterlist.CityAdapter
import by.olegkopeykin.weather.ui.screens.selectcity.adaptersearch.SearchCityAdapter
import javax.inject.Inject

class SelectCityFragment : BaseMvvmFragment<SelectCityViewModel, SelectCityRouter>() {

    @Inject
    lateinit var vmFactory: SelectCityViewModel.Factory

    override val viewModel: SelectCityViewModel by viewModels { vmFactory }

    override fun onAttach(context: Context) {
        context.appComponent().inject(this)
        super.onAttach(context)
    }

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
        ).also { binding ->
            binding.viewModel = viewModel
            binding.adapterSearchCity = SearchCityAdapter(viewModel.searchCityListener)
            binding.adapterCity = CityAdapter(viewModel.cityListener)
        }
        return binding.root
    }
}