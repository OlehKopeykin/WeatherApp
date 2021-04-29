package by.olegkopeykin.weather.common

import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
fun <T> ObservableField<T>.toFlow(): Flow<T> = callbackFlow {
	val observableField = this@toFlow
	val callback = object : Observable.OnPropertyChangedCallback() {
		override fun onPropertyChanged(dataBindingObservable: Observable, propertyId: Int) {
			if (dataBindingObservable == observableField) {
				// To avoid blocking you can configure channel capacity using
				// either buffer(Channel.CONFLATED) or buffer(Channel.UNLIMITED) to avoid overfill
				sendBlocking(observableField.get())
			}
		}
	}
	observableField.addOnPropertyChangedCallback(callback)
	/*
	 * Suspends until either 'onCompleted'/'onApiError' from the callback is invoked
	 * or flow collector is cancelled (e.g. by 'take(1)' or because a collector's coroutine was cancelled).
	 * In both cases, callback will be properly unregistered.
	 */
	awaitClose { observableField.removeOnPropertyChangedCallback(callback) }
}

@ExperimentalCoroutinesApi
fun ObservableBoolean.toFlow(): Flow<Boolean> = callbackFlow {
	val observableField = this@toFlow
	val callback = object : Observable.OnPropertyChangedCallback() {
		override fun onPropertyChanged(dataBindingObservable: Observable, propertyId: Int) {
			if (dataBindingObservable == observableField) {
				// To avoid blocking you can configure channel capacity using
				// either buffer(Channel.CONFLATED) or buffer(Channel.UNLIMITED) to avoid overfill
				sendBlocking(observableField.get())
			}
		}
	}
	observableField.addOnPropertyChangedCallback(callback)
	/*
	 * Suspends until either 'onCompleted'/'onApiError' from the callback is invoked
	 * or flow collector is cancelled (e.g. by 'take(1)' or because a collector's coroutine was cancelled).
	 * In both cases, callback will be properly unregistered.
	 */
	awaitClose { observableField.removeOnPropertyChangedCallback(callback) }
}