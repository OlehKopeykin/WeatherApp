package by.olegkopeykin.weather.common

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.olegkopeykin.weather.app.AppWeather

//@Suppress("UNCHECKED_CAST")
//inline fun <reified ViewModelT : BaseMvvmViewModel<R>, R : MvvmRouter> BaseMvvmFragment<ViewModelT, R>.viewModelLazyInstance() =
//    viewModels<ViewModelT> {
//        (requireActivity().application as AppWeather).appComponent.inject(this)
//    }
//    lazy {
//        ViewModelProvider(this, object : ViewModelProvider.Factory {
//            override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                return this@viewModelLazyInstance.di.run {
//                    val viewModel by instance<ViewModelT>()
//                    viewModel
//                } as T
//            }
//        })[ViewModelT::class.java]
//    }

//@Suppress("UNCHECKED_CAST")
//inline fun <reified ViewModelT : BaseMvvmViewModel<R>, R : MvvmRouter, reified Param : Any> BaseMvvmFragment<ViewModelT, R>.viewModelLazyFactory(
//    crossinline param: () -> Param
//) =
//    lazy {
//        ViewModelProvider(this, object : ViewModelProvider.Factory {
//            override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                return this@viewModelLazyFactory.di.run {
//                    val viewModelFactory: (Param) -> ViewModelT by factory()
//                    viewModelFactory(param())
//                } as T
//            }
//        })[ViewModelT::class.java]
//    }

