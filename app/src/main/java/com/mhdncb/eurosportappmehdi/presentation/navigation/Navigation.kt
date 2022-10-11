package com.mhdncb.eurosportappmehdi.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mhdncb.eurosportappmehdi.presentation.screen.home.Home
import com.mhdncb.eurosportappmehdi.utils.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination

    Scaffold(
        containerColor = White,
        modifier = Modifier
            .navigationBarsPadding()
    ) {
        Box(
            modifier = Modifier
        ) {
            ScreenController(
                navController = navController,
                paddingValues = it
            )
        }
    }
}

@Composable
fun ScreenController(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(
            route = Screen.Home.route
        ) {
            Home(
                onItemClick = {
                    navController.navigate(Screen.Details.route.replace("{itemId}", it.toString()))
                }
            )
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument(name = "itemId") {
                    NavType.StringType
                }
            )
        ) {
            val itemId = it.arguments?.getString(
                "itemId"
            )
            if (itemId != null) {

            }
        }

    }
}
