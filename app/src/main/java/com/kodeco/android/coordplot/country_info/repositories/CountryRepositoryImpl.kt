package com.kodeco.android.coordplot.country_info.repositories


import com.kodeco.android.coordplot.country_info.database.dao.CountryDao
import com.kodeco.android.coordplot.country_info.models.Country
import com.kodeco.android.coordplot.country_info.networking.CountryService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CountryRepositoryImpl(
    private val apiService: CountryService,
    private val countryDao: CountryDao,
) : CountryRepository {

    private val _countries: MutableStateFlow<List<Country>> = MutableStateFlow(emptyList())
    override val countries: StateFlow<List<Country>> = _countries.asStateFlow()

    private val _localStorageEnabled = MutableStateFlow(true)

    override suspend fun fetchCountries() {
        val favorites = if (_localStorageEnabled.value) {
            countryDao.getFavoriteCountries()
        } else {
            emptyList()
        }

        try {
            _countries.value = emptyList()
            val countriesResponse = apiService.getAllCountries()

            if (_localStorageEnabled.value) {
                countryDao.deleteAllCountries()
            }

            _countries.value = if (countriesResponse.isSuccessful) {
                val countries = countriesResponse.body()!!
                    .toMutableList()
                    .map { country ->
                        country.copy(isFavorite = favorites.any { it.commonName == country.commonName })
                    }
                if (_localStorageEnabled.value) {
                    countryDao.insertAllCountries(*countries.toTypedArray())
                }
                countries
            } else {
                throw Throwable("Request failed: ${countriesResponse.errorBody()}")
            }
        } catch (e: Exception) {
            if (_localStorageEnabled.value) {
                _countries.value = countryDao.getAllCountries()
            }
        }
    }

    override fun getCountry(countryIndex: Int): Country? =
        _countries.value.getOrNull(countryIndex)

    override suspend fun favorite(country: Country) {
        val index = _countries.value.indexOf(country)
        val mutableCountries = _countries.value.toMutableList()
        val updatedCountry = country.copy(isFavorite = country.isFavorite.not())

        mutableCountries[index] = updatedCountry
        if (_localStorageEnabled.value) {
            countryDao.updateFavorite(updatedCountry)
        }
        _countries.value = mutableCountries.toList()
    }
}
