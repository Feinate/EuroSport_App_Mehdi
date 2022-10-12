package com.mhdncb.eurosportappmehdi.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mhdncb.eurosportappmehdi.domain.entity.Story
import com.mhdncb.eurosportappmehdi.presentation.screen.details.Details
import com.mhdncb.eurosportappmehdi.presentation.screen.home.Home
import com.mhdncb.eurosportappmehdi.utils.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {

    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
    ) {
        ScreenController(
            navController = navController
        )
    }
}

@Composable
fun ScreenController(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(
            route = Screen.Home.route
        ) {
            Home(
                onStoryClicked = { story ->
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "story",
                        value = story
                    )
                    navController.navigate(Screen.Details.route)
                }
            )
        }
        composable(
            route = Screen.Details.route
        ) {
            val userObject = navController.previousBackStackEntry?.savedStateHandle?.get<Story>("story")
            userObject?.let {
                Details(story = userObject) {
                    navController.navigateUp()
                }
            }
        }

    }
}
