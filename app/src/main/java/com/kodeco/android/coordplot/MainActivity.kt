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
                            onNavigateToCountryInfo = { navController.navigate("country_info") }
                        )
                    }
                    composable("coordplot") {
                        PlotSurface(configuration.orientation)
                    }
                    composable("country_info") {
                        CountryInfo()
                    }
                }
            }
        }
    }
}
