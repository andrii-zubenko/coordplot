package com.kodeco.android.coordplot.country_info.models

data class Country(
    val commonName: String,
    val mainCapital: String,
    val population: Long,
    val area: Float,
    val flagUrl: String,
    val isFavorite: Boolean = false,
)
