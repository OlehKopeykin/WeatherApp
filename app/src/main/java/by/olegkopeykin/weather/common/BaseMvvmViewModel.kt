package by.olegkopeykin.weather.common

import androidx.lifecycle.ViewModel

abstract class BaseMvvmViewModel<Router : MvvmRouter>(val router: Router) : BaseViewModel()

abstract class BaseViewModel : ViewModel() {

    public override fun onCleared() {
        super.onCleared()
    }

    open fun onViewCreated() {

    }

    open fun onResume() {

    }

    open fun onPause() {

    }

    open fun onViewDestroyed() {

    }
}