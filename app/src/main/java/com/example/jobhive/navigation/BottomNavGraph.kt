package com.example.jobhive.navigation

import MapScreen
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jobhive.screens.BottomBarScreen
import com.example.jobhive.screens.*
import com.example.jobhive.screens.profile.ProfileScreen
import com.example.jobhive.screens.profile.createProfileViewModel
import com.example.jobhive.screens.swmap.SWMapScreen
import com.example.jobhive.screens.swmap.createSwMapViewModel
import com.example.jobhive.screens.usamap.createUsaMapViewModel

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen(viewModel = viewModel(factory = createProfileViewModel()))
        }
        composable(route = BottomBarScreen.Map.route) {
            MapScreen(viewModel = viewModel(factory = createUsaMapViewModel()))
        }
        composable(route = BottomBarScreen.SWMap.route) {
            SWMapScreen(viewModel = viewModel(factory = createSwMapViewModel()))
        }
        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen()
        }
    }
}

