package com.kodeco.android.coordplot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalConfiguration
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kodeco.android.coordplot.country_info.screens.CountryInfoScreen
import com.kodeco.android.coordplot.ui.theme.CoordPlotTheme
import com.kodeco.android.coordplot.screens.MainScreen
import com.kodeco.android.coordplot.coordplotter.screens.PlotSurface
import com.kodeco.android.coordplot.country_info.CountryListData.data
import com.kodeco.android.coordplot.country_info.screens.CountryDetailsScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContent {
            CoordPlotTheme {
                val configuration = LocalConfiguration.current
                val navController = rememberNavController()
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
                        CountryInfoScreen(navController)
                    }
                    composable(
                        route = "country_details/{index}",
                        arguments = listOf(
                            navArgument("index") { type = NavType.IntType }
                        )
                    ) { backStackEntry ->
                        val index = backStackEntry.arguments?.getInt("index") ?: 0
                        CountryDetailsScreen(
                            countryData = data,
                            countryIndex = index,
                            onBackClicked = { navController.navigateUp() })
                    }
                }
            }
        }
    }
}
