package com.kodeco.android.coordplot

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kodeco.android.coordplot.coordplotter.screens.PlotSurface
import com.kodeco.android.coordplot.country_info.repositories.CountryRepository
import com.kodeco.android.coordplot.country_info.ui.screens.aboutscreen.AboutScreen
import com.kodeco.android.coordplot.country_info.ui.screens.countrydetails.CountryDetailsScreen
import com.kodeco.android.coordplot.country_info.ui.screens.countrydetails.CountryDetailsViewModelFactory
import com.kodeco.android.coordplot.country_info.ui.screens.countryinfo.CountryInfoScreen
import com.kodeco.android.coordplot.country_info.ui.screens.countryinfo.CountryInfoViewModel
import com.kodeco.android.coordplot.country_info.ui.screens.countryinfo.CountryInfoViewModelFactory

@Composable
fun Navigation(
    repository: CountryRepository
) {
    val configuration = LocalConfiguration.current
    val navController = rememberNavController()

    val countryInfoViewModel: CountryInfoViewModel = viewModel(
        factory = CountryInfoViewModelFactory(repository = repository)
    )

    NavHost(navController = navController, startDestination = MainScreen.route) {
        composable(MainScreen.route) {
            MainScreen(
                onNavigateToCoordPlot = { navController.navigate(CoordPlot.route) },
                onNavigateToCountryInfo = { navController.navigate(CountryInfo.route) }
            )
        }
        composable(CoordPlot.route) {
            PlotSurface(configuration.orientation)
        }
        composable(CountryInfo.route) {
            CountryInfoScreen(
                viewModel = countryInfoViewModel,
                onCountryRowTap = { countryIndex ->
                    navController.navigateToSingleCountry(countryIndex)
                },
                onAboutTap = { navController.navigate(AboutScreen.route) }
            )
        }
        composable(
            route = CountryDetails.routeWithArg,
            arguments = CountryDetails.arguments
        ) { backStackEntry ->
            val index = backStackEntry.arguments?.getInt(CountryDetails.countryIndexArg) ?: 0
            CountryDetailsScreen(
                onBackTap = { navController.navigateUp() },
                viewModel = viewModel(
                    factory = CountryDetailsViewModelFactory(
                        countryIndex = index,
                        repository = repository,
                    ),
                )
            )
        }
        composable(AboutScreen.route) {
            AboutScreen(onBackTap = { navController.navigateUp() })
        }
    }
}

private fun NavHostController.navigateToSingleCountry(countryIndex: Int) {
    this.navigate("${CountryDetails.route}/$countryIndex")
}

