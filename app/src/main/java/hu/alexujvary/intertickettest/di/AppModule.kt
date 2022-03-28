package hu.alexujvary.intertickettest.di

import android.content.Context
import dagger.Module
import dagger.Provides
import hu.alexujvary.intertickettest.application.App
import hu.alexujvary.intertickettest.network.RestApi
import hu.alexujvary.intertickettest.network.RetrofitService
import javax.inject.Singleton

@Module
class AppModule(private val appContext: App) {

    @Provides
    fun providesContext(): Context {
        return appContext
    }

    @Provides
    @Singleton
    fun providesRetrofitService(): RetrofitService {
        return RetrofitService.getInstance()
    }

    @Provides
    @Singleton
    fun providesRestApi(): RestApi {
        return RetrofitService.getInstance().getRestApi()
    }
}