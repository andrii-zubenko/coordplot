package com.kodeco.android.coordplot.country_info

import com.kodeco.android.coordplot.country_info.model.Country

object CountryListData {
    var data = listOf<Country>()
        private set

    fun setData(newData: List<Country>) {
        data = newData
    }
}