package com.kodeco.android.coordplot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalConfiguration
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kodeco.android.coordplot.components.CountryInfo
import com.kodeco.android.coordplot.components.PlotSurface
import com.kodeco.android.coordplot.ui.theme.CoordPlotTheme
import com.kodeco.android.coordplot.screens.MainScreen

class MainActivity : ComponentActivity() {

    val mainScreen = "mainscreen"
    val coordPlot = "coordplot"
    val countryInfo = "country_info"

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
                            onNavigateToCountryInfo = { navController.navigate(countryInfo) }
                        )
                    }
                    composable(coordPlot) {
                        PlotSurface(configuration.orientation)
                    }
                    composable(countryInfo) {
                        CountryInfo()
                    }
                }
            }
        }
    }
}
