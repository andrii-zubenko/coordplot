package com.kodeco.android.coordplot.country_info

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object Flows {
    private val _tapFlow: StateFlow<Int> = MutableStateFlow(0)
    val tapFlow: StateFlow<Int> = _tapFlow
    fun setNewTapValue(newValue: Int) {
        (_tapFlow as MutableStateFlow).value = newValue
    }

    private val _backFlow: StateFlow<Int> = MutableStateFlow(0)
    val backFlow: StateFlow<Int> = _backFlow
    fun setNewBackValue(newValue: Int) {
        (_backFlow as MutableStateFlow).value = newValue
    }

    private val _counterFlow: StateFlow<Int> = MutableStateFlow(0)
    val counterFlow: StateFlow<Int> = _counterFlow
    fun setNewCounterValue(newValue: Int) {
        (_counterFlow as MutableStateFlow).value = newValue
    }
}
