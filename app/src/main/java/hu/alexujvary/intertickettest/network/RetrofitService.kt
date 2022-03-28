package hu.alexujvary.intertickettest.network

import hu.alexujvary.intertickettest.BuildConfig
import hu.alexujvary.intertickettest.application.Constants
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RetrofitService @Inject constructor() {

    private val networkTimeoutSeconds: Long = 30
    private var httpClient: OkHttpClient
    private var retrofit: Retrofit
    private var restApi: RestApi

    companion object {

        private var instance: RetrofitService? = null

        fun getInstance(): RetrofitService {
            if (instance == null) {
                instance = RetrofitService()
            }
            return instance!!
        }
    }

    init {
        httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor())
            .connectTimeout(networkTimeoutSeconds, TimeUnit.SECONDS)
            .readTimeout(networkTimeoutSeconds, TimeUnit.SECONDS)
            .writeTimeout(networkTimeoutSeconds, TimeUnit.SECONDS)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

        restApi = retrofit.create(RestApi::class.java)
    }

    private fun loggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return interceptor
    }

    fun getRestApi(): RestApi {
        return restApi
    }
}