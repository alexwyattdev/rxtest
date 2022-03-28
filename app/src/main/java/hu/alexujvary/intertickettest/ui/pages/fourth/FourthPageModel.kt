package hu.alexujvary.intertickettest.ui.pages.fourth

import hu.alexujvary.intertickettest.model.CountryResponse
import hu.alexujvary.intertickettest.network.RestApi
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class FourthPageModel @Inject constructor(private val restApi: RestApi) : FourthPageContract.Model {

    override fun loadCountries(): Observable<List<CountryResponse>> = restApi.getCountries()
}