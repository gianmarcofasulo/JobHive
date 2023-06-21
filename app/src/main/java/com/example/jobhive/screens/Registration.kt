package com.example.jobhive.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.jobhive.R
import com.example.jobhive.navigation.DestinationGraph
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff

@Composable
fun RegistrationScreen(navController: NavController) {
    val width = LocalConfiguration.current.screenWidthDp
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logoblank),
                null,
                Modifier.size(150.dp),
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Full name", color = Color.Black) },
                    textStyle = MaterialTheme.typography.body1.copy(color = Color.Black),
                    modifier = Modifier
                        .width(width * 0.9.dp)
                        .height(60.dp)
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email address", color = Color.Black) },
                    textStyle = MaterialTheme.typography.body1.copy(color = Color.Black),
                    modifier = Modifier
                        .width(width * 0.9.dp)
                        .height(60.dp)
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password", color = Color.Black) },
                    textStyle = MaterialTheme.typography.body1.copy(color = Color.Black),
                    modifier = Modifier
                        .width(width * 0.9.dp)
                        .height(60.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (passwordVisible)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff

                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = image, "password",
                                tint = Color.Black
                            )
                        }
                    }
                )
                Button(
                    onClick = { /* registration logic */
                        navController.navigate(DestinationGraph.Tab.route) {
                            popUpTo(DestinationGraph.Login.route) {
                                inclusive = true
                            }
                        }
                    },
                    modifier = Modifier
                        .width(width * 0.9.dp)
                        .height(60.dp)
                ) {
                    Text(
                        text = "Create account",
                        style = MaterialTheme.typography.button,
                        color = Color.White
                    )
                }
            }

        }
    }
}
