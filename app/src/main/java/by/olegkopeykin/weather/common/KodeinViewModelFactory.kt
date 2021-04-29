@file:Suppress("UNCHECKED_CAST")

package by.olegkopeykin.weather.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.generic.factory
import org.kodein.di.generic.instance

inline fun <reified ViewModelT : BaseMvvmViewModel<R>, R : MvvmRouter> BaseMvvmFragment<ViewModelT, R>.viewModelLazyInstance() =
	lazy {
		ViewModelProvider(this, object : ViewModelProvider.Factory {
			override fun <T : ViewModel> create(modelClass: Class<T>): T {
				return this@viewModelLazyInstance.kodein.run {
					val viewModel by instance<ViewModelT>()
					viewModel
				} as T
			}
		}).get(ViewModelT::class.java)
	}

inline fun <reified ViewModelT : BaseMvvmViewModel<R>, R : MvvmRouter, reified Param> BaseMvvmFragment<ViewModelT, R>.viewModelLazyFactory(
	crossinline param: () -> Param
) =
	lazy {
		ViewModelProvider(this, object : ViewModelProvider.Factory {
			override fun <T : ViewModel> create(modelClass: Class<T>): T {
				return this@viewModelLazyFactory.kodein.run {
					val viewModelFactory: (Param) -> ViewModelT by factory()
					viewModelFactory(param())
				} as T
			}
		}).get(ViewModelT::class.java)
	}

