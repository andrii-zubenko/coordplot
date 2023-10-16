package com.kodeco.android.coordplot.country_info.repositories

import android.util.Log
import com.kodeco.android.coordplot.country_info.model.Country
import com.kodeco.android.coordplot.country_info.networking.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CountryRepositoryImpl(private val apiService: ApiService) :
    CountryRepository {
    private val TAG = "CountryRepositoryImpl"
    private var countries = listOf<Country>()
    override suspend fun fetchCountries(refreshNeeded: Boolean): Flow<List<Country>> {
        if (!refreshNeeded && countries.isNotEmpty()) {
            return flow {
                emit(countries)
            }
        }
        delay(3000)
        return flow {
            val countriesResponse = apiService.getAllCountries()

            if (countriesResponse.isSuccessful && countriesResponse.body() != null) {
                val countryList = countriesResponse.body()?.toList() ?: emptyList()
                emit(countryList)
                countries = countryList
            } else {
                Log.e(TAG, "Error: ${countriesResponse.message()}")
                emit(emptyList())
                countries = emptyList()
            }
        }.catch { exception ->
            Log.e(TAG, "Error: ${exception.message}")
            emit(emptyList())
            countries = emptyList()
        }.flowOn(Dispatchers.IO)
    }

    override fun getCountry(countryIndex: Int): Country? {
        return countries.getOrNull(countryIndex)
    }
}
