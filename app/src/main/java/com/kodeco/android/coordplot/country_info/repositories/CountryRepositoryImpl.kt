package com.kodeco.android.coordplot.country_info.repositories

import com.kodeco.android.coordplot.country_info.model.Country
import com.kodeco.android.coordplot.country_info.networking.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CountryRepositoryImpl(private val apiService: ApiService) :
    CountryRepository {

    private var favorites = setOf<String>()

    private val _countries: MutableStateFlow<List<Country>> = MutableStateFlow(emptyList())
    override val countries: StateFlow<List<Country>> = _countries.asStateFlow()

    override suspend fun fetchCountries() {
        val countriesResponse = apiService.getAllCountries()

        _countries.value = emptyList()
        _countries.value = try {
            if (countriesResponse.isSuccessful) {
                countriesResponse.body()!!
                    .toMutableList()
                    .map { country ->
                        country.copy(isFavorite = favorites.contains(country.name.common))
                    }
            } else {
                throw Throwable("Request failed: ${countriesResponse.message()}")
            }
        } catch (e: Exception) {
            throw Throwable("Request failed: ${e.message}")
        }
    }

    override fun getCountry(countryIndex: Int): Country? =
        _countries.value.getOrNull(countryIndex)

    override fun favorite(country: Country) {
        favorites = if (favorites.contains(country.name.common)) {
            favorites - country.name.common
        } else {
            favorites + country.name.common
        }
        val index = _countries.value.indexOf(country)
        val mutableCountries = _countries.value.toMutableList()
        mutableCountries[index] =
            mutableCountries[index].copy(isFavorite = favorites.contains(country.name.common))
        _countries.value = mutableCountries.toList()
    }
}
