package com.example.jobhive.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jobhive.R
import com.example.jobhive.datastore.StoreDataUser
import kotlinx.coroutines.launch

@Composable
fun LoginScreen() {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val datastore = StoreDataUser(context)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2E3D51))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 120.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Logo",
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = "Welcome back",
                style = MaterialTheme.typography.h4,
                color = Color.White,
                modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email address", color = Color.White) },
                textStyle = MaterialTheme.typography.body1.copy(color = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 32.dp)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = Color.White) },
                textStyle = MaterialTheme.typography.body1.copy(color = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 32.dp)
                    .padding(top = 16.dp)
            )
            Button(
                onClick = {
                    /*login logic*/
                    scope.launch {
                        datastore.saveEmail(email)
                        datastore.savePassword(password)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 32.dp, vertical = 24.dp)
            ) {
                Text(
                    text = "Log in",
                    style = MaterialTheme.typography.button,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
@Preview
fun LoginScreenPreview() {
    LoginScreen()
}