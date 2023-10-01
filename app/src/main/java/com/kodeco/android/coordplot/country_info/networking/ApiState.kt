package com.kodeco.android.coordplot.country_info.networking

import android.os.Parcelable
import com.kodeco.android.coordplot.country_info.model.Country
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class ApiState : Parcelable {
    class Success(val data: List<Country>) : ApiState()
    data object Failure : ApiState()
    object Loading : ApiState()
    object Empty : ApiState()
}
