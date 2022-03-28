package hu.alexujvary.intertickettest.di

import dagger.Component
import hu.alexujvary.intertickettest.ui.pages.first.FirstPageFragment
import hu.alexujvary.intertickettest.ui.pages.fourth.FourthPageFragment
import hu.alexujvary.intertickettest.ui.pages.second.SecondPageFragment
import hu.alexujvary.intertickettest.ui.pages.third.ThirdPageFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(item: FirstPageFragment)
    fun inject(item: SecondPageFragment)
    fun inject(item: ThirdPageFragment)
    fun inject(item: FourthPageFragment)
}