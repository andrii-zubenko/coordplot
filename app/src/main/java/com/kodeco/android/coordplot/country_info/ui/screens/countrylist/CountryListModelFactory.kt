package com.kodeco.android.coordplot.country_info.ui.screens.countrylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kodeco.android.coordplot.country_info.repositories.CountryRepository

class CountryListModelFactory(private val repository: CountryRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        CountryListViewModel(repository) as T
}
