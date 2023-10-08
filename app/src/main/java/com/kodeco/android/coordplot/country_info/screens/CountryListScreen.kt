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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kodeco.android.coordplot.R
import com.kodeco.android.coordplot.country_info.CountryListData
import com.kodeco.android.coordplot.country_info.CountryListData.data
import com.kodeco.android.coordplot.country_info.components.CountryList
import com.kodeco.android.coordplot.country_info.networking.ApiService
import com.kodeco.android.coordplot.country_info.networking.ApiState
import com.kodeco.android.coordplot.country_info.networking.apiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

const val TAG = "CountryListScreen"

@Composable
fun CountryInfoScreen(navigation: NavController) {

    var apiState by rememberSaveable { mutableStateOf<ApiState>(ApiState.Empty) }

    LaunchedEffect(Unit) {
        if (data.isNotEmpty()) {
            return@LaunchedEffect
        }
        getCountryInfoFlow(apiService).collect { currentApiState ->
            apiState = currentApiState
        }
    }


    when (apiState) {
        is ApiState.Success -> {
            CountryList(
                countries = data,
                navigation = navigation
            )
        }

        is ApiState.Failure -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(R.string.oops_something_is_not_right))
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

private fun getCountryInfoFlow(apiService: ApiService): Flow<ApiState> = flow {
    emit(ApiState.Loading)
    val countriesResponse = apiService.getAllCountries()
    // Simulate a network delay
    delay(2000L)

    if (countriesResponse.isSuccessful && countriesResponse.body() != null) {
        CountryListData.setData(countriesResponse.body()?.toList() ?: emptyList())
        emit(ApiState.Success)
    } else {
        Log.e(TAG, "Error: ${countriesResponse.message()}")
        emit(ApiState.Failure)
    }
}.catch {
    Log.e(TAG, "Error: ${it.message}")
    emit(ApiState.Failure)
}
