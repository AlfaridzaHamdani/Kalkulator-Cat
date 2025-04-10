package com.alfa0059.kalkulatorakumulasicat.navigation

sealed class Screen(val route : String) {
    data object Home: Screen("mainScreen")
    data object About: Screen("aboutScreen")
}