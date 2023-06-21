package com.example.jobhive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.compose.rememberNavController
import com.example.jobhive.navigation.SetupNavGraph
import com.example.jobhive.ui.theme.JobHiveTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JobHiveTheme() {
                val navController = rememberNavController()
                SetupNavGraph(navController)
            }
        }
    }
}
