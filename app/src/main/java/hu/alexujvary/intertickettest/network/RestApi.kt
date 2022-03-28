package hu.alexujvary.intertickettest.network

import hu.alexujvary.intertickettest.model.CountryResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface RestApi {
    @GET("europe")
    fun getCountries(): Observable<List<CountryResponse>>
}