package by.olegkopeykin.weather.ui.screens.selectcity

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.olegkopeykin.interactors.cities.CityInteractor
import by.olegkopeykin.model.domain.CityModel
import by.olegkopeykin.weather.common.BaseMvvmViewModel
import by.olegkopeykin.weather.common.toObservable
import by.olegkopeykin.weather.ui.screens.selectcity.adapterlist.CityListener
import by.olegkopeykin.weather.ui.screens.selectcity.adaptersearch.SearchCityListener
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SelectCityViewModel(
    router: SelectCityRouter,
    private val interactorCity: CityInteractor,
) : BaseMvvmViewModel<SelectCityRouter>(router) {

    val listSearch = ObservableField<List<CityModel>>(listOf())
    val listCities = ObservableField<List<CityModel>>(listOf())

    val inputSearch = ObservableField<String>("")

    val searchCityListener = object : SearchCityListener {
        override fun onCityClick(city: CityModel) {
            router.hideKeyboard()
            inputSearch.set("")
            interactorCity.saveCityDB(city)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                }, {
                    it.printStackTrace()
                }).toComposite()
        }
    }

    val cityListener = object : CityListener {
        override fun onCityClick(city: CityModel) {

        }

        override fun onFavoriteClick(city: CityModel) {
            interactorCity.setFavoriteCity(city)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                }, {
                    it.printStackTrace()
                }).toComposite()
        }

        override fun onDeleteClick(city: CityModel) {
            interactorCity.removeCityDB(city)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                }, {
                    it.printStackTrace()
                }).toComposite()
        }
    }

    init {
        inputSearch.toObservable()
            .debounce(500, TimeUnit.MILLISECONDS)
            .switchMapSingle {
                interactorCity.getCityByName(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listSearch.set(it)
            }, {
                it.printStackTrace()
            }).toComposite()

        interactorCity.getCities()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listCities.set(it)
            }, {
                it.printStackTrace()
            }).toComposite()
    }

    class Factory @Inject constructor(
        private val router: SelectCityRouter,
        private val interactorCity: CityInteractor,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SelectCityViewModel(router, interactorCity) as T
        }
    }
}