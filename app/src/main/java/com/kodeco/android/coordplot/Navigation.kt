package com.kodeco.android.coordplot

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kodeco.android.coordplot.coordplotter.screens.PlotSurface
import com.kodeco.android.coordplot.country_info.ui.screens.aboutscreen.AboutScreen
import com.kodeco.android.coordplot.country_info.ui.screens.countrydetails.CountryDetailsScreen
import com.kodeco.android.coordplot.country_info.ui.screens.countrylist.CountryListScreen
import com.kodeco.android.coordplot.country_info.ui.screens.settingsscreen.SettingsScreen

@Composable
fun Navigation() {
    val configuration = LocalConfiguration.current
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = MainScreen.route) {
        composable(MainScreen.route) {
            MainScreen(
                onNavigateToCoordPlot = { navController.navigate(CoordPlot.route) },
                onNavigateToCountryInfo = { navController.navigate(CountryList.route) }
            )
        }
        composable(CoordPlot.route) {
            PlotSurface(configuration.orientation)
        }
        composable(CountryList.route) {
            CountryListScreen(
                viewModel = hiltViewModel(),
                onCountryRowTap = { countryIndex ->
                    navController.navigateToSingleCountry(countryIndex)
                },
                onAboutTap = { navController.navigate(AboutScreen.route) },
                onSettingsTap = { navController.navigate(SettingsScreen.route) },
                settingsScreenViewModel = hiltViewModel()
            )
        }
        composable(
            route = CountryDetails.routeWithArg,
            arguments = CountryDetails.arguments
        ) { backStackEntry ->
            val index = backStackEntry.arguments?.getInt(CountryDetails.countryIndexArg) ?: 0
            CountryDetailsScreen(
                onBackTap = { navController.navigateUp() },
                viewModel = hiltViewModel(),
                countryIndex = index
            )
        }
        composable(AboutScreen.route) {
            AboutScreen(onBackTap = { navController.navigateUp() })
        }
        composable(SettingsScreen.route) {
            SettingsScreen(viewModel = hiltViewModel(), onBackTap = { navController.navigateUp() })
        }
    }
}

private fun NavHostController.navigateToSingleCountry(countryIndex: Int) {
    this.navigate("${CountryDetails.route}/$countryIndex")
}
