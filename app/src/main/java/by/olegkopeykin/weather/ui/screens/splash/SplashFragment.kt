package by.olegkopeykin.weather.ui.screens.splash

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
import by.olegkopeykin.weather.databinding.FragmentSplashBinding
import javax.inject.Inject

class SplashFragment : BaseMvvmFragment<SplashViewModel, SplashRouter>() {

    @Inject
    lateinit var vmFactory: SplashViewModel.Factory

    override val viewModel: SplashViewModel by viewModels { vmFactory }

    override fun onAttach(context: Context) {
        context.appComponent().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentSplashBinding>(
            inflater,
            R.layout.fragment_splash,
            container,
            false
        ).also {
            it.viewModel = viewModel
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.doRouting()
    }
}