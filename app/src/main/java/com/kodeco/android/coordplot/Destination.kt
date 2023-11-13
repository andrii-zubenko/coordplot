package com.kodeco.android.coordplot

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destination {
    val route: String
}

object MainScreen : Destination {
    override val route = "main_screen"
}

object CoordPlot : Destination {
    override val route = "coord_plot"
}

object CountryList : Destination {
    override val route = "country_list"
}

object CountryDetails : Destination {
    override val route = "country_details"
    const val countryIndexArg = "index"
    val routeWithArg = "$route/{index}"
    val arguments = listOf(
        navArgument(countryIndexArg) { type = NavType.IntType }
    )
}

object AboutScreen : Destination {
    override val route = "about_screen"
}

object SettingsScreen : Destination {
    override val route = "settings_screen"
}
