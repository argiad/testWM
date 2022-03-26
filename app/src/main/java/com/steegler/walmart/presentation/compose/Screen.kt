package com.steegler.walmart.presentation.compose

sealed class Screen(val route: String) {
    object CountryListScreen: Screen("country_list_screen")
}