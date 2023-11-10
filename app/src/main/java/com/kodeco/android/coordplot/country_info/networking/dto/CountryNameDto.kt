package com.kodeco.android.coordplot.country_info.networking.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryNameDto(val common: String)