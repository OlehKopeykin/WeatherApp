package by.olegkopeykin.weather.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.factory
import org.kodein.di.instance

@Suppress("UNCHECKED_CAST")
inline fun <reified ViewModelT : BaseMvvmViewModel<R>, R : MvvmRouter> BaseMvvmFragment<ViewModelT, R>.viewModelLazyInstance() =
    lazy {
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return this@viewModelLazyInstance.di.run {
                    val viewModel by instance<ViewModelT>()
                    viewModel
                } as T
            }
        })[ViewModelT::class.java]
    }

@Suppress("UNCHECKED_CAST")
inline fun <reified ViewModelT : BaseMvvmViewModel<R>, R : MvvmRouter, reified Param : Any> BaseMvvmFragment<ViewModelT, R>.viewModelLazyFactory(
    crossinline param: () -> Param
) =
    lazy {
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return this@viewModelLazyFactory.di.run {
                    val viewModelFactory: (Param) -> ViewModelT by factory()
                    viewModelFactory(param())
                } as T
            }
        })[ViewModelT::class.java]
    }

