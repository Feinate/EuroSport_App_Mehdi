package com.mhdncb.eurosportappmehdi.utils

sealed class Screen(val name: String, val route: String)  {

    object Home: Screen(name = "Home", route = "Home")
    object Details: Screen(name = "Details", route = "Details")

}