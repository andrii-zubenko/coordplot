package com.kodeco.android.coordplot.country_info.networking

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class CountryInfoState : Parcelable {
    data object Success : CountryInfoState()
    data object Failure : CountryInfoState()
    data object Loading : CountryInfoState()
}
