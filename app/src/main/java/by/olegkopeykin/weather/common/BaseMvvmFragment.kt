package by.olegkopeykin.weather.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein

abstract class BaseMvvmFragment<VM : BaseMvvmViewModel<Router>, Router : MvvmRouter> : Fragment(), KodeinAware {

    override val kodein: Kodein by kodein()
    abstract val viewModel: VM
    protected val disposables = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewCreated()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onViewDestroyed()
        disposables.clear()
    }

    fun Disposable.addDisposable() {
        disposables.add(this)
    }
}