package com.kodeco.android.coordplot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalConfiguration
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kodeco.android.coordplot.country_info.screens.CountryInfoScreen
import com.kodeco.android.coordplot.ui.theme.CoordPlotTheme
import com.kodeco.android.coordplot.screens.MainScreen
import com.kodeco.android.coordplot.coordplotter.screens.PlotSurface
import com.kodeco.android.coordplot.country_info.model.Country
import com.kodeco.android.coordplot.country_info.networking.ApiState
import com.kodeco.android.coordplot.country_info.screens.CountryDetailsScreen

class MainActivity : ComponentActivity() {

    private val mainScreen = "mainscreen"
    private val coordPlot = "coordplot"
    private val countryList = "country_list"
    private val countryDetails = "country_details"

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContent {
            CoordPlotTheme {
                val configuration = LocalConfiguration.current
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = mainScreen) {
                    composable(mainScreen) {
                        MainScreen(
                            onNavigateToCoordPlot = { navController.navigate(coordPlot) },
                            onNavigateToCountryInfo = { navController.navigate(countryList) }
                        )
                    }
                    composable(coordPlot) {
                        PlotSurface(configuration.orientation)
                    }
                    composable(countryList) {
                        CountryInfoScreen(
                            onNavigateToCountryDetailsScreen = {
                                navController.navigate(countryDetails)
                            }
                        )
                    }
                    composable(countryDetails) {
                        CountryDetailsScreen(onBackClicked = { navController.navigateUp() })
                    }
                }
            }
        }
    }
}
