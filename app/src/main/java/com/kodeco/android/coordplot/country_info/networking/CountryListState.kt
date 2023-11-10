package com.kodeco.android.coordplot.country_info.networking

import com.kodeco.android.coordplot.country_info.models.Country

sealed class CountryListState {
    data class Success(val countryList: List<Country>) : CountryListState()
    data class Error(val error: Throwable) : CountryListState()
    data object Loading : CountryListState()
}
