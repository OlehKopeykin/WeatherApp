package by.olegkopeykin.weather.ui.screens.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import by.olegkopeykin.weather.R
import by.olegkopeykin.weather.common.BaseMvvmFragment
import by.olegkopeykin.weather.common.viewModelLazyInstance
import by.olegkopeykin.weather.databinding.FragmentSplashBinding

class SplashFragment: BaseMvvmFragment<SplashViewModel, SplashRouter>() {

    override val viewModel: SplashViewModel by viewModelLazyInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
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