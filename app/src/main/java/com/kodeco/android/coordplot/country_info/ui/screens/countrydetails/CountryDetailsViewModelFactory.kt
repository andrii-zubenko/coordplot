package com.kodeco.android.coordplot.country_info.ui.screens.countrydetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kodeco.android.coordplot.country_info.repositories.CountryRepository

class CountryDetailsViewModelFactory(
    private val countryIndex: Int,
    private val repository: CountryRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        CountryDetailsViewModel(countryIndex, repository) as T
}