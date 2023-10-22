package com.kodeco.android.coordplot.country_info.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kodeco.android.coordplot.MainScreen
import com.kodeco.android.coordplot.coordplotter.screens.PlotSurface
import com.kodeco.android.coordplot.country_info.networking.apiService
import com.kodeco.android.coordplot.country_info.repositories.CountryRepositoryImpl
import com.kodeco.android.coordplot.country_info.ui.screens.countrydetails.CountryDetailsScreen
import com.kodeco.android.coordplot.country_info.ui.screens.countrydetails.CountryDetailsViewModelFactory
import com.kodeco.android.coordplot.country_info.ui.screens.countryinfo.CountryInfoScreen
import com.kodeco.android.coordplot.country_info.ui.screens.countryinfo.CountryInfoViewModel
import com.kodeco.android.coordplot.country_info.ui.screens.countryinfo.CountryInfoViewModelFactory

@Composable
fun Navigation() {
    val configuration = LocalConfiguration.current
    val navController = rememberNavController()
    val repository = CountryRepositoryImpl(apiService)

    val countersTopBarViewModel = CountersTopBarViewModel()
    val countryInfoViewModel: CountryInfoViewModel =
        viewModel(
            factory = CountryInfoViewModelFactory(repository = repository)
        )

    NavHost(navController = navController, startDestination = "mainscreen") {
        composable("mainscreen") {
            MainScreen(
                onNavigateToCoordPlot = { navController.navigate("coordplot") },
                onNavigateToCountryInfo = { navController.navigate("countryInfo") }
            )
        }
        composable("coordplot") {
            PlotSurface(configuration.orientation)
        }
        composable("countryInfo") {
            CountryInfoScreen(
                navigation = navController,
                countersTopBar = {
                    CountersTopBar(
                        onRefreshClick = {
                            navController.navigate("countryInfo")
                            countryInfoViewModel.fetchCountryList(refreshNeeded = true)
                        },
                        viewModel = countersTopBarViewModel
                    )
                },
                viewModel = countryInfoViewModel,
                countersTopBarViewModel = countersTopBarViewModel
            )
        }
        composable(
            route = "countryDetails/{index}",
            arguments = listOf(
                navArgument("index") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val index = backStackEntry.arguments?.getInt("index") ?: 0
            CountryDetailsScreen(
                onBackClicked = { navController.navigateUp() },
                countersTopBar = {
                    CountersTopBar(
                        onRefreshClick = {
                            navController.navigate("countryInfo")
                            countryInfoViewModel.fetchCountryList(refreshNeeded = true)
                        },
                        viewModel = countersTopBarViewModel
                    )
                },
                viewModel = viewModel(
                    factory = CountryDetailsViewModelFactory(
                        countryIndex = index,
                        repository = repository,
                    ),
                ),
                countersTopBarViewModel = countersTopBarViewModel
            )
        }
    }
}
