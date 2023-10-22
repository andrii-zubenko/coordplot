package com.kodeco.android.coordplot.country_info.ui.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CountersTopBarViewModel : ViewModel() {
    var taps by mutableStateOf(0)
        private set

    var backs by mutableStateOf(0)
        private set

    var currentTheme by mutableStateOf(Theme.LIGHT)
        private set

    fun tap() {
        taps += 1
    }

    fun back() {
        backs += 1
    }

    fun setTheme(theme: Theme) {
        currentTheme = theme
    }
}

enum class Theme {
    LIGHT, DARK
}
