package com.kodeco.android.coordplot.country_info.ui.screens.countrydetails

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.kodeco.android.coordplot.country_info.models.Country
import com.kodeco.android.coordplot.country_info.repositories.CountryRepository

class CountryDetailsViewModel(
    countryIndex: Int,
    repository: CountryRepository
) : ViewModel() {
    private var _selectedCountry: MutableState<Country?> =
        mutableStateOf(repository.getCountry(countryIndex))
    val selectedCountry = _selectedCountry
}
