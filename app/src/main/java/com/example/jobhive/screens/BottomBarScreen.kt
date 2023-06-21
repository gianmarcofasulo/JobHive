package com.example.jobhive.screens

import androidx.compose.foundation.Image
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource


sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Profile : BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )

    object Map : BottomBarScreen(
        route = "map",
        title = "USAMap",
        icon = Icons.Default.Map
    )

    object SWMap : BottomBarScreen(
        route = "swmap",
        title = "SWMap",
        icon = Icons.Default.Map
    )


    object Settings : BottomBarScreen(
        route = "settings",
        title = "Settings",
        icon = Icons.Default.Settings
    )

}
