package by.olegkopeykin.weather.ui.screens

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import by.olegkopeykin.weather.R
import by.olegkopeykin.weather.app.AppWeather
import by.olegkopeykin.weather.databinding.ActivityMainBinding
import by.olegkopeykin.weather.ui.screens.citydetails.CityDetailsFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    private val navigator: NavController
        get() = Navigation.findNavController(
            this,
            R.id.navHostFragment
        )

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        (application as AppWeather).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.viewModel = viewModel

        viewModel.nextScreen.observe(this, { screen ->
            try {
                when (screen) {
                    is Screens.PrevScreen -> {
                        hideKeyboard()
                        navigator.popBackStack()
                    }
                    is Screens.SelectCity -> {
                        navigator.navigate(R.id.action_select_city)
                    }
                    is Screens.CityDetails -> {
                        val args = Bundle()
                        args.putParcelable(CityDetailsFragment.WEATHER_MODEL, screen.weather)
                        navigator.navigate(R.id.action_city_details, args)
                    }
                    is Screens.CityList -> {
                        navigator.navigate(R.id.action_city_list)
                    }
                    is Screens.HideKeyboard -> {
                        hideKeyboard()
                    }
                    else -> {

                    }
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        })
    }

    private fun hideKeyboard() {
        var view = currentFocus
        if (view == null) {
            view = findViewById(android.R.id.content)
            if (view == null) {
                view = View(this)
            }
        }
        val inputMethodManager =
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        view.clearFocus()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        hideKeyboard()
    }
}