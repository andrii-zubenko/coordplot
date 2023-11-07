package com.kodeco.android.coordplot.country_info.repositories

import android.util.Log
import com.kodeco.android.coordplot.country_info.database.dao.CountryDao
import com.kodeco.android.coordplot.country_info.models.Country
import com.kodeco.android.coordplot.country_info.networking.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CountryRepositoryImpl(
    private val apiService: ApiService,
    private val countryDao: CountryDao
) : CountryRepository {

    private var favorites = setOf<String>()

    private val _countries: MutableStateFlow<List<Country>> = MutableStateFlow(emptyList())
    override val countries: StateFlow<List<Country>> = _countries.asStateFlow()

    override suspend fun fetchCountries() {
        try {
            val countriesResponse = apiService.getAllCountries()

            _countries.value = emptyList()
            _countries.value = if (countriesResponse.isSuccessful) {
                countriesResponse.body()!!
                    .toMutableList()
                    .map { country ->
                        country.copy(isFavorite = favorites.contains(country.commonName))
                    }
            } else {
                throw Throwable("Request failed: ${countriesResponse.message()}")
            }

            // After fetching, update the local database
            countryDao.insertAll(_countries.value)

        } catch (e: Exception) {
            Log.e("CountryRepositoryImpl", "fetchCountries: ${e.message}")
            // Handle exception (e.g., no internet connection)
            // Fall back to local data if available
            val localData = countryDao.getAllCountries()

            if (localData.isNotEmpty()) {
                _countries.value = localData
            } else {
                throw Throwable("Request failed: ${e.message}")
            }
        }
    }

    override fun getCountry(countryIndex: Int): Country? =
        _countries.value.getOrNull(countryIndex)

    override suspend fun favorite(country: Country) {
        favorites = if (favorites.contains(country.commonName)) {
            favorites - country.commonName
        } else {
            favorites + country.commonName
        }

        // Update local database
        countryDao.updateFavorite(country.commonName, favorites.contains(country.commonName))

        val index = _countries.value.indexOf(country)
        val mutableCountries = _countries.value.toMutableList()
        mutableCountries[index] =
            mutableCountries[index].copy(isFavorite = favorites.contains(country.commonName))
        _countries.value = mutableCountries.toList()
    }
}
