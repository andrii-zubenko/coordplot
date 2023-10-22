package com.kodeco.android.coordplot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.kodeco.android.coordplot.country_info.ui.components.Navigation
import com.kodeco.android.coordplot.ui.theme.CoordPlotTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContent {
            CoordPlotTheme {
                Navigation()
            }
        }
    }
}
