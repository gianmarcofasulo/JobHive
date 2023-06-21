package com.example.jobhive.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.jobhive.screens.MainScreen
import com.example.jobhive.screens.RegistrationScreen

object Graph {
    const val ROOT = "root_graph"
    const val MAIN = "main_graph"
    const val TAB = "tab_graph"
}

sealed class DestinationGraph(val route: String) {
    object Login : DestinationGraph("LoginScreen")
    object Tab : DestinationGraph("Tab")
}

@ExperimentalMaterialApi
@Composable
fun SetupNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        route = Graph.ROOT,
        startDestination = Graph.MAIN
    ) {
        loginGraph(navHostController)
        tabGraph(navHostController)
    }
}

fun NavGraphBuilder.tabGraph(navHostController: NavHostController) {
    navigation(route = Graph.TAB, startDestination = DestinationGraph.Tab.route) {
        composable(route = DestinationGraph.Tab.route) {
            MainScreen()
        }
    }
}

fun NavGraphBuilder.loginGraph(navHostController: NavHostController) {
    navigation(route = Graph.MAIN, startDestination = DestinationGraph.Login.route) {
        composable(route = DestinationGraph.Login.route) {
            RegistrationScreen(navHostController)
        }
    }
}