package com.kodeco.android.coordplot.country_info.ui.screens.settingsscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodeco.android.coordplot.country_info.prefdatastore.CountryPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private val prefs: CountryPrefs
) : ViewModel() {

    private val _localStorageEnabled = MutableStateFlow(true)
    private val _favoritesFeatureEnabled = MutableStateFlow(true)

    val localStorageEnabled: StateFlow<Boolean> = _localStorageEnabled
    val favoritesFeatureEnabled: StateFlow<Boolean> = _favoritesFeatureEnabled

    init {
        viewModelScope.launch {
            prefs.getLocalStorageEnabled().collect {
                _localStorageEnabled.value = it
            }
        }
        viewModelScope.launch {
            prefs.getFavoritesFeatureEnabled().collect {
                _favoritesFeatureEnabled.value = it
            }
        }
    }

    fun toggleLocalStorage() {
        viewModelScope.launch {
            prefs.toggleLocalStorage()
        }
    }

    fun toggleFavoritesFeature() {
        viewModelScope.launch {
            prefs.toggleFavoritesFeature()
        }
    }
}
