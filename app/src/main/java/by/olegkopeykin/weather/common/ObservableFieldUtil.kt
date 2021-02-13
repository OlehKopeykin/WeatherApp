package by.olegkopeykin.weather.common

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import io.reactivex.Observable

fun <T> ObservableField<T>.toObservable() : Observable<T> {
    val observableField = this
    return Observable.create<T>{ emitter ->
        if(!emitter.isDisposed){
            observableField.get()?.let { emitter.onNext(it) }
        }
        val callback = object : androidx.databinding.Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(dataBindingObservable: androidx.databinding.Observable, propertyId: Int) {
                if (dataBindingObservable == observableField) {
                    emitter.onNext(observableField.get()!!)
                }
            }
        }
        observableField.addOnPropertyChangedCallback(callback)
        emitter.setCancellable { observableField.removeOnPropertyChangedCallback(callback) }
    }
}

fun ObservableBoolean.toObservable() : Observable<Boolean> {
    val observableBoolean = this
    return Observable.create<Boolean> { emitter ->
        if(!emitter.isDisposed){
            emitter.onNext(observableBoolean.get())
        }
        val callback = object : androidx.databinding.Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(dataBindingObservable: androidx.databinding.Observable?, propertyId: Int) {
                if (dataBindingObservable == observableBoolean) {
                    emitter.onNext(observableBoolean.get())
                }
            }
        }
        observableBoolean.addOnPropertyChangedCallback(callback)
        emitter.setCancellable { observableBoolean.removeOnPropertyChangedCallback(callback) }
    }
}