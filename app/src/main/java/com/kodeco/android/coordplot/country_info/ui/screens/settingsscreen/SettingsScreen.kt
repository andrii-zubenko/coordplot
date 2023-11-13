package com.kodeco.android.coordplot.country_info.ui.screens.settingsscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kodeco.android.coordplot.R
import com.kodeco.android.coordplot.country_info.prefdatastore.CountryPrefs
import kotlinx.coroutines.flow.Flow

@Composable
fun SettingsScreen(viewModel: SettingsScreenViewModel, onBackTap: () -> Unit) {
    val localStorageEnabledState = viewModel.localStorageEnabled.collectAsState()
    val favoritesFeatureEnabledState = viewModel.favoritesFeatureEnabled.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.setttings_title)) },
                navigationIcon = {
                    IconButton(onClick = { onBackTap() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FeatureToggle(toggleName = stringResource(R.string.enable_local_storage),
                checked = localStorageEnabledState.value,
                onCheckedChange = {
                    viewModel.toggleLocalStorage()
                })
            FeatureToggle(toggleName = stringResource(R.string.enable_favorites_feature),
                checked = favoritesFeatureEnabledState.value,
                onCheckedChange = {
                    viewModel.toggleFavoritesFeature()
                })
        }
    }
}

@Composable
fun FeatureToggle(
    toggleName: String,
    checked: Boolean = true,
    onCheckedChange: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 8.dp,
                vertical = 8.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = toggleName,
            fontSize = 22.sp
        )
        Switch(
            checked = checked,
            onCheckedChange = {
                onCheckedChange()
            }
        )
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(
        onBackTap = {},
        viewModel = SettingsScreenViewModel(
            prefs = object : CountryPrefs {
                override fun getLocalStorageEnabled(): Flow<Boolean> {
                    TODO("Not yet implemented")
                }

                override fun getFavoritesFeatureEnabled(): Flow<Boolean> {
                    TODO("Not yet implemented")
                }

                override suspend fun toggleLocalStorage() {
                    TODO("Not yet implemented")
                }

                override suspend fun toggleFavoritesFeature() {
                    TODO("Not yet implemented")
                }
            }
        )
    )
}
