package com.kodeco.android.coordplot.country_info.ui.screens.countrydetails

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodeco.android.coordplot.country_info.models.Country
import com.kodeco.android.coordplot.country_info.networking.CountryListState
import com.kodeco.android.coordplot.country_info.repositories.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryDetailsViewModel @Inject constructor(
    private val repository: CountryRepository
) : ViewModel() {
    private var _selectedCountry: MutableState<Country?> = mutableStateOf(null)
    val selectedCountry = _selectedCountry

    fun getSelectedCountry(countryIndex: Int) {
        viewModelScope.launch {
            _selectedCountry.value = repository.getCountry(countryIndex)
        }
    }
}
