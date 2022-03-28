package hu.alexujvary.intertickettest.di

import hu.alexujvary.intertickettest.application.App
import hu.alexujvary.intertickettest.ui.pages.first.FirstPageFragment
import hu.alexujvary.intertickettest.ui.pages.fourth.FourthPageFragment
import hu.alexujvary.intertickettest.ui.pages.second.SecondPageFragment
import hu.alexujvary.intertickettest.ui.pages.third.ThirdPageFragment

object Injector {

    private lateinit var appComponent: AppComponent

    fun init(application: App) {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(application)).build()
    }

    fun inject(forItem: FirstPageFragment) {
        appComponent.inject(forItem)
    }

    fun inject(forItem: SecondPageFragment) {
        appComponent.inject(forItem)
    }

    fun inject(forItem: ThirdPageFragment) {
        appComponent.inject(forItem)
    }

    fun inject(forItem: FourthPageFragment) {
        appComponent.inject(forItem)
    }
}