package com.example.jobhive.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jobhive.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Welcome in Job Hive") }) },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Aggiungi l'immagine qui
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo di Job Hive",
                    modifier = Modifier.size(120.dp)
                )
                Text(
                    text = "What is Job Hive",
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "With Job Hive, you can enter your work preferences, such as occupation type, hobbies, and much more. The app will use this information to suggest the most suitable city for you." ,
                    style = MaterialTheme . typography . body1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Button(
                    onClick = { /* Navigate to next screen */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Start to use Job Hive")
                }
            }
        }
    )
}



