package by.olegkopeykin.services.network.api

import by.olegkopeykin.model.network.CityResponse
import by.olegkopeykin.services.network.NetworkServiceImpl
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface CityApi {

    @GET("geo/1.0/direct")
    fun getCitiesByName(@Query("q") requestNameCity: String,
                        @Query("limit") limit: Int = 5,
                        @Query("appid") appid: String = NetworkServiceImpl.KEY_API): Single<List<CityResponse>>
}