package by.olegkopeykin.weather.common

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseMvvmViewModel<Router : MvvmRouter>(val router: Router) : BaseViewModel()

abstract class BaseViewModel : ViewModel() {

    private val disposables = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun Disposable.addDisposable(disposable: CompositeDisposable) {
        disposables.add(disposable)
    }

    fun Disposable.toComposite(): Disposable {
        disposables.add(this)
        return this
    }

    public override fun onCleared() {
        super.onCleared()
        disposables.clear()
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