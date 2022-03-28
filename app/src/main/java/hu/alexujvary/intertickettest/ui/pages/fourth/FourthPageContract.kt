package hu.alexujvary.intertickettest.ui.pages.fourth

import hu.alexujvary.intertickettest.base.BaseContract
import hu.alexujvary.intertickettest.model.CountryResponse
import io.reactivex.rxjava3.core.Observable

interface FourthPageContract : BaseContract {

    interface View : BaseContract.BaseView {
        fun countriesLoaded(countries: List<CountryResponse>)
    }

    interface Presenter {
        fun loadCountries()
    }

    interface Model {
        fun loadCountries(): Observable<List<CountryResponse>>
    }
}