package by.olegkopeykin.weather.ui.screens.citylist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import by.olegkopeykin.weather.R
import by.olegkopeykin.weather.app.appComponent
import by.olegkopeykin.weather.common.BaseMvvmFragment
import by.olegkopeykin.weather.databinding.FragmentCityListBinding
import by.olegkopeykin.weather.ui.screens.citylist.adapter.CityWeatherAdapter
import javax.inject.Inject

class CityListFragment : BaseMvvmFragment<CityListViewModel, CityListRouter>() {

    @Inject
    lateinit var vmFactory: CityListViewModel.Factory

    override val viewModel: CityListViewModel by viewModels { vmFactory }

    override fun onAttach(context: Context) {
        context.appComponent().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentCityListBinding>(
            inflater,
            R.layout.fragment_city_list,
            container,
            false
        ).also { binding->
            binding.viewModel = viewModel
            binding.adapterCity = CityWeatherAdapter(viewModel.listener)
            binding.imgvChangeThemeColor.setOnClickListener {
                if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }
        return binding.root
    }
}