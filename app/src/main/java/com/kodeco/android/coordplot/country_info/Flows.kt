package com.kodeco.android.coordplot.country_info

import com.kodeco.android.coordplot.country_info.model.Country
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

object Flows {
    init {
        GlobalScope.launch {
            while (true) {
                delay(1000)
                _counterFlow.value += 1
            }
        }
        GlobalScope.launch {
            _uniqueCountriesSelectedFlow.emit(_selectedCountriesList)
        }
    }

    private val _tapFlow = MutableStateFlow(0)
    val tapFlow = _tapFlow.asStateFlow()
    fun tap() {
        _tapFlow.value += 1
    }

    private val _backFlow = MutableStateFlow(0)
    val backFlow = _backFlow.asStateFlow()
    fun tapBack() {
        _backFlow.value += 1
    }

    private val _counterFlow = MutableStateFlow(0)
    val counterFlow = _counterFlow.asStateFlow()

    private val _currentTheme = MutableStateFlow(Theme.LIGHT)
    val currentTheme: StateFlow<Theme> = _currentTheme.asStateFlow()

    fun setTheme(theme: Theme) {
        _currentTheme.value = theme
    }

    private val _uniqueCountriesSelectedFlow = MutableSharedFlow<List<Country>>()
    val uniqueCountriesSelectedFlow: SharedFlow<List<Country>> = _uniqueCountriesSelectedFlow

    private val _selectedCountriesList = mutableListOf<Country>()
    fun addCountry(country: Country) {
        if (!_selectedCountriesList.contains(country)) {
            _selectedCountriesList.add(country)
            GlobalScope.launch {
                _uniqueCountriesSelectedFlow.emit(_selectedCountriesList)
            }
        }
    }
}

enum class Theme {
    LIGHT, DARK
}
