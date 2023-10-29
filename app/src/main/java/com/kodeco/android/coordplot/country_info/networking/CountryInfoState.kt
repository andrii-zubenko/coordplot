package com.kodeco.android.coordplot.country_info.networking

import android.os.Parcelable
import com.kodeco.android.coordplot.country_info.model.Country
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class CountryInfoState : Parcelable {
    data class Success(val countryList: List<Country>) : CountryInfoState()
    data class Error(val error: Throwable) : CountryInfoState()
    data object Loading : CountryInfoState()
}
