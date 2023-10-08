package com.kodeco.android.coordplot.country_info

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

object Flows {
    init {
        GlobalScope.launch {
            while (true) {
                delay(1000)
                incrementCounterValue()
            }
        }
    }

    private val _tapFlow = MutableStateFlow(0)
    val tapFlow = _tapFlow.asStateFlow()
    fun incrementTapValue() {
        _tapFlow.value += 1
    }

    private val _backFlow = MutableStateFlow(0)
    val backFlow = _backFlow.asStateFlow()
    fun incrementBackValue() {
        _backFlow.value += 1
    }

    private val _counterFlow = MutableStateFlow(0)
    val counterFlow = _counterFlow.asStateFlow()
    private fun incrementCounterValue() {
        _counterFlow.value += 1
    }
}
