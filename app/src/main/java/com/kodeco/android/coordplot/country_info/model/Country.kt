package com.kodeco.android.coordplot.country_info.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Country(
    val name: CountryName,
    val capital: List<String>?,
    val population: Long,
    val area: Double,
    val flags: CountryFlags,
    val isFavorite: Boolean = false,
) : Parcelable
