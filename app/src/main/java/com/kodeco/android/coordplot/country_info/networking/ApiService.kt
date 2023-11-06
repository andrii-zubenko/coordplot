package com.kodeco.android.coordplot.country_info.networking

import WrappedCountryList
import com.kodeco.android.coordplot.country_info.models.Country
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("v3.1/all")
    @WrappedCountryList
    suspend fun getAllCountries(): Response<List<Country>>
}
