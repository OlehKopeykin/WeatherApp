package by.olegkopeykin.services.preferences.common

import android.content.SharedPreferences
import kotlin.reflect.KProperty

class BooleanPreferenceDelegate(private val prefs: SharedPreferences, private val key: String, private val defValue: Boolean) {
	operator fun getValue(thisRef: Any?, property: KProperty<*>): Boolean {
		return prefs.getBoolean(key, defValue)
	}

	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
		prefs.edit().putBoolean(key, value).apply()
	}
}

class IntPreferenceDelegate(private val prefs: SharedPreferences, private val key: String, private val defValue: Int) {
	operator fun getValue(thisRef: Any?, property: KProperty<*>): Int {
		return prefs.getInt(key, defValue)
	}

	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
		prefs.edit().putInt(key, value).apply()
	}
}

class LongPreferenceDelegate(private val prefs: SharedPreferences, private val key: String, private val defValue: Long) {
	operator fun getValue(thisRef: Any?, property: KProperty<*>): Long {
		return prefs.getLong(key, defValue)
	}

	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) {
		prefs.edit().putLong(key, value).apply()
	}
}

class FloatPreferenceDelegate(private val prefs: SharedPreferences, private val key: String, private val defValue: Float) {
	operator fun getValue(thisRef: Any?, property: KProperty<*>): Float {
		return prefs.getFloat(key, defValue)
	}

	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Float) {
		prefs.edit().putFloat(key, value).apply()
	}
}

class StringPreferenceDelegate(private val prefs: SharedPreferences, private val key: String, private val defValue: String) {
	operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
		return prefs.getString(key, defValue) ?: defValue
	}

	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
		prefs.edit().putString(key, value).apply()
	}
}
