package com.kodeco.android.coordplot.country_info.networking

import android.os.Parcelable
import com.kodeco.android.coordplot.country_info.models.Country

sealed class CountryInfoState {
    data class Success(val countryList: List<Country>) : CountryInfoState()
    data class Error(val error: Throwable) : CountryInfoState()
    data object Loading : CountryInfoState()
}
