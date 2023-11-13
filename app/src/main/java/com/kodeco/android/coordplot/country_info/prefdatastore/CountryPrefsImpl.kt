package com.kodeco.android.coordplot.country_info.prefdatastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private const val STORE_NAME = "country_prefs"

class CountryPrefsImpl @Inject constructor(@ApplicationContext context: Context) : CountryPrefs {

    private val Context.dataStore by preferencesDataStore(name = STORE_NAME)
    private val dataStore = context.dataStore

    private val LOCAL_STORAGE_KEY = booleanPreferencesKey("local_storage_enabled")
    private val FAVORITES_FEATURE_KEY = booleanPreferencesKey("favorites_feature_enabled")

    override fun getLocalStorageEnabled(): Flow<Boolean> =
        dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[LOCAL_STORAGE_KEY] ?: true
        }

    override fun getFavoritesFeatureEnabled(): Flow<Boolean> =
        dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[FAVORITES_FEATURE_KEY] ?: true
        }

    override suspend fun toggleLocalStorage() {
        dataStore.edit { preferences ->
            val current = preferences[LOCAL_STORAGE_KEY] ?: true
            preferences[LOCAL_STORAGE_KEY] = !current
        }
    }

    override suspend fun toggleFavoritesFeature() {
        dataStore.edit { preferences ->
            val current = preferences[FAVORITES_FEATURE_KEY] ?: true
            preferences[FAVORITES_FEATURE_KEY] = !current
        }
    }
}