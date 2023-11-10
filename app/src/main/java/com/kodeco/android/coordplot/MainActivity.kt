package com.kodeco.android.coordplot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.kodeco.android.coordplot.country_info.database.CountryInfoDatabase.Companion.buildDatabase
import com.kodeco.android.coordplot.country_info.networking.apiService
import com.kodeco.android.coordplot.country_info.repositories.CountryRepositoryImpl
import com.kodeco.android.coordplot.ui.theme.CoordPlotTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        val db = buildDatabase(applicationContext)
        val countryDao = db.countryDao()
        val repository = CountryRepositoryImpl(apiService, countryDao)

        super.onCreate(savedInstanceState)
        setContent {
            CoordPlotTheme {
                Navigation(repository = repository)
            }
        }
    }
}
