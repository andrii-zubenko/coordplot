package com.kodeco.android.coordplot.country_info.repositories

import com.kodeco.android.coordplot.country_info.models.Country
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    val countries: Flow<List<Country>>

    suspend fun fetchCountries()
    fun getCountry(countryIndex: Int): Country?
    suspend fun favorite(country: Country)
}
