package com.kodeco.android.coordplot.country_info.networking

import android.os.Parcelable
import com.kodeco.android.coordplot.country_info.model.Country
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class ApiState : Parcelable {
    data object Success : ApiState()
    data object Failure : ApiState()
    data object Loading : ApiState()
    data object Empty : ApiState()
}
