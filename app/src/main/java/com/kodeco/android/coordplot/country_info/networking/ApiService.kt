package com.kodeco.android.coordplot.country_info.networking

import com.kodeco.android.coordplot.country_info.model.Country
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("v3.1/all")
    suspend fun getAllCountries(): Response<List<Country>>
}
