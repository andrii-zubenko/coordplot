package com.kodeco.android.coordplot.country_info.repositories

import com.kodeco.android.coordplot.country_info.model.Country
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    suspend fun fetchCountries(refreshNeeded: Boolean): Flow<List<Country>>
    fun getCountry(countryIndex: Int): Country?
}
