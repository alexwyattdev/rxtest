package hu.alexujvary.intertickettest.application

import android.app.Application
import hu.alexujvary.intertickettest.di.Injector
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
        Timber.plant(Timber.DebugTree())
    }
}