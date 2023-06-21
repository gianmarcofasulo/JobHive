package com.example.jobhive.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen() {
    var darkMode by remember { mutableStateOf(false) }
    var notificationEnabled by remember { mutableStateOf(true) }
    var selectedLanguage by remember { mutableStateOf("English") }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Settings", style = MaterialTheme.typography.h5)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Dark Mode")
            Switch(
                checked = darkMode,
                onCheckedChange = { darkMode = it }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Enable Notifications")
            Switch(
                checked = notificationEnabled,
                onCheckedChange = { notificationEnabled = it }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Language")
            DropdownMenu(
                expanded = false,
                onDismissRequest = { /* Dismiss the menu */ },
                modifier = Modifier.wrapContentSize()
            ) {
                DropdownMenuItem(onClick = { selectedLanguage = "English" }) {
                    Text("English")
                }
                DropdownMenuItem(onClick = { selectedLanguage = "Spanish" }) {
                    Text("Spanish")
                }
                DropdownMenuItem(onClick = { selectedLanguage = "French" }) {
                    Text("French")
                }
            }
            Text(text = selectedLanguage)
        }
    }
}

@Composable
@Preview
fun SettingsScreenPreview() {
    SettingsScreen()
}