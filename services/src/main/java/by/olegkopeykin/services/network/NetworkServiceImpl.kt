package by.olegkopeykin.services.network

import by.olegkopeykin.services.BuildConfig
import by.olegkopeykin.services.network.api.CityApi
import by.olegkopeykin.services.network.api.WeatherApi
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class NetworkServiceImpl : NetworkService {

    override val weatherApi: WeatherApi
        get() = apiControlRetrofit.create(WeatherApi::class.java)

    override val cityApi: CityApi
        get() = apiControlRetrofit.create(CityApi::class.java)

    private val builder: Retrofit.Builder = Retrofit.Builder()
        .apply {
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create(Gson()))
            baseUrl("http://api.openweathermap.org/")
        }

    private val apiControlRetrofit = builder
        .client(apiHttpClient())
        .build()

    private fun apiHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            readTimeout(READ_TIMEOUT_SEC, TimeUnit.SECONDS)
            connectTimeout(CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS)
            addInterceptor(errorInterceptor())
            protocols(arrayListOf(Protocol.HTTP_2, Protocol.HTTP_1_1))
            val interceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG){
                interceptor.level = HttpLoggingInterceptor.Level.BODY
            }else{
                interceptor.level = HttpLoggingInterceptor.Level.NONE
            }
            addInterceptor(interceptor)
        }.build()
    }

    @Throws(Exception::class, retrofit2.HttpException::class)
    private fun errorInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            val body = response.body
            if (response.isSuccessful.not() && body != null) {
                val errorResponse = Response.error<ResponseBody>(body, response)
                throw HttpException(errorResponse)
            }
            response
        }
    }

    companion object {
        private const val READ_TIMEOUT_SEC = 10L
        private const val CONNECT_TIMEOUT_SEC = 10L
        const val KEY_API = "5ec5429e0611f82571e5177349c3f96f"
    }
}