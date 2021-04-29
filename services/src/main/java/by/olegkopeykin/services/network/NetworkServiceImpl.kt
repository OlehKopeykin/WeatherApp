package by.olegkopeykin.services.network

import by.olegkopeykin.services.BuildConfig
import by.olegkopeykin.services.network.api.CityApi
import by.olegkopeykin.services.network.api.WeatherApi
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


class NetworkServiceImpl : NetworkService {

	override val weatherApi: WeatherApi
		get() = apiControlRetrofit.create(WeatherApi::class.java)

	override val cityApi: CityApi
		get() = apiControlRetrofit.create(CityApi::class.java)

	private val builder: Retrofit.Builder = Retrofit.Builder()
		.apply {
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
			protocols(arrayListOf(Protocol.HTTP_2, Protocol.HTTP_1_1))
			addInterceptor(errorInterceptor())
			addInterceptor(loggingInterceptor())
		}.build()
	}

	@Throws(Exception::class, retrofit2.HttpException::class)
	private fun errorInterceptor(): Interceptor {
		return Interceptor { chain ->
			val response = chain.proceed(chain.request())
			val body = response.body()
			if (response.isSuccessful.not() && body != null) {
				throw IOException("${response.code()}")
			}
			response
		}
	}

	private fun loggingInterceptor(): Interceptor {
		return HttpLoggingInterceptor().also {
			if (BuildConfig.DEBUG) {
				it.level = HttpLoggingInterceptor.Level.BODY
			} else {
				it.level = HttpLoggingInterceptor.Level.NONE
			}
		}
	}

	companion object {
		private const val READ_TIMEOUT_SEC = 10L
		private const val CONNECT_TIMEOUT_SEC = 10L
		const val KEY_API = "5ec5429e0611f82571e5177349c3f96f"
	}
}