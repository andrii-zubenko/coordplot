package com.kodeco.android.coordplot.country_info.prefdatastore

import kotlinx.coroutines.flow.Flow

interface CountryPrefs {
    fun getLocalStorageEnabled(): Flow<Boolean>
    fun getFavoritesFeatureEnabled(): Flow<Boolean>

    suspend fun toggleLocalStorage()
    suspend fun toggleFavoritesFeature()
}