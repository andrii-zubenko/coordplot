package com.kodeco.android.coordplot.country_info.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class CountryFlags(
    val png: String
) : Parcelable
