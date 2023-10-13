package com.kodeco.android.coordplot

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kodeco.android.coordplot.coordplotter.screens.PlotSurface
import com.kodeco.android.coordplot.country_info.CountryListData
import com.kodeco.android.coordplot.country_info.Flows
import com.kodeco.android.coordplot.country_info.components.CountersTopBar
import com.kodeco.android.coordplot.country_info.screens.CountryDetailsScreen
import com.kodeco.android.coordplot.country_info.screens.CountryInfoScreen

@Composable
fun Navigation() {
    val configuration = LocalConfiguration.current
    val navController = rememberNavController()
    val tapState = Flows.tapFlow.collectAsState()
    val backState = Flows.backFlow.collectAsState()
    val countriesSelected =
        Flows.uniqueCountriesSelectedFlow.collectAsState(initial = emptyList())

    NavHost(navController = navController, startDestination = "mainscreen") {
        composable("mainscreen") {
            MainScreen(
                onNavigateToCoordPlot = { navController.navigate("coordplot") },
                onNavigateToCountryInfo = { navController.navigate("country_list") }
            )
        }
        composable("coordplot") {
            PlotSurface(configuration.orientation)
        }
        composable("country_list") {
            CountryInfoScreen(
                navigation = navController,
                countersTopBar = {
                    CountersTopBar(
                        taps = tapState.value,
                        backs = backState.value,
                        countriesSelected = countriesSelected.value.size,
                        onRefreshClick = {
                            CountryListData.dataNeedsRefreshing = true
                            navController.navigate("country_list")
                        }
                    )
                }
            )
        }
        composable(
            route = "country_details/{index}",
            arguments = listOf(
                navArgument("index") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val index = backStackEntry.arguments?.getInt("index") ?: 0
            CountryDetailsScreen(
                countryData = CountryListData.data,
                countryIndex = index,
                onBackClicked = { navController.navigateUp() },
                countersTopBar = {
                    CountersTopBar(
                        taps = tapState.value,
                        backs = backState.value,
                        countriesSelected = countriesSelected.value.size,
                        onRefreshClick = {
                            CountryListData.dataNeedsRefreshing = true
                            navController.navigate("country_list")
                        }
                    )
                }
            )
        }
    }

}