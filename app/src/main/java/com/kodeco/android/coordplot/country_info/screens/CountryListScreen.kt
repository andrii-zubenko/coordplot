package com.kodeco.android.coordplot.country_info.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kodeco.android.coordplot.country_info.components.CountryList
import com.kodeco.android.coordplot.country_info.networking.ApiState
import com.kodeco.android.coordplot.country_info.networking.apiService

@Composable
fun CountryInfoScreen(onNavigateToCountryDetailsScreen: () -> Unit) {
    val TAG = "CountryListScreen"
    var apiState by rememberSaveable { mutableStateOf<ApiState>(ApiState.Empty) }

    LaunchedEffect(Unit) {
        try {
            apiState = ApiState.Loading
            val countriesResponse = apiService.getAllCountries()
            // Simulate a network delay
//            delay(5000L)

            apiState = if (countriesResponse.isSuccessful && countriesResponse.body() != null) {
                val countryList = countriesResponse.body()?.toList() ?: emptyList()
                ApiState.Success(countryList)
            } else {
                Log.e(TAG, "Error: ${countriesResponse.message()}")
                ApiState.Failure
            }
        } catch (e: Throwable) {
            Log.e(TAG, "Error: $e")
            ApiState.Failure
        }

    }

    when (val state = apiState) {
        is ApiState.Success -> {
            CountryList(
                countries = state.data,
                onNavigateToCountryDetailsScreen = onNavigateToCountryDetailsScreen
            )
        }

        is ApiState.Failure -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Oops! Something is not right :(")
            }
        }

        ApiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(48.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        ApiState.Empty -> {

        }
    }
}
