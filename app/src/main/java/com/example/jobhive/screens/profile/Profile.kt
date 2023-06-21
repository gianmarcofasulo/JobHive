package com.example.jobhive.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.jobhive.R
import com.example.jobhive.datastore.StoreDataUser
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {
    var expandedLocation by remember { mutableStateOf(false) }
    val expanded by remember { mutableStateOf(false) }
    var expandedTemperature by remember { mutableStateOf(false) }
    var expandedNight by remember { mutableStateOf(false) }
    var expandedOccupation by remember { mutableStateOf(false) }
    var expandedHobby by remember { mutableStateOf(false) }
    var textFiledSize by remember { mutableStateOf(androidx.compose.ui.geometry.Size.Zero) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val dataStore = StoreDataUser(context)
    val salary = dataStore.getSalary.collectAsState(initial = 0)
    val nightLife = dataStore.getNightlife.collectAsState(initial = "")
    val occupation = dataStore.getOccupation.collectAsState(initial = "")
    val location = dataStore.getLocation.collectAsState(initial = "")
    val temperature = dataStore.getTemperature.collectAsState(initial = "")
    val hobby = dataStore.getHobby.collectAsState(initial = "")

    LaunchedEffect(key1 = true) {
        viewModel.setDataOnStart(
            preferences = Preferences(
                salary = salary.value ?: 0,
                city = location.value ?: "",
                hobby = hobby.value ?: "",
                occupation = occupation.value ?: "",
                hangoutTime = nightLife.value ?: "",
                temperature = temperature.value ?: ""
            )
        )
    }

    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.TopCenter
    )


    {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.preferences),
                contentDescription = "Logo di Job Hive",
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = "The data entry screen helps you find the best city to live in. Enter your preferences for cost of living, air quality, climate, proximity to amenities, and more. The screen will show you the cities that best suit your needs.",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            var currency by remember { mutableStateOf("USD") }

            OutlinedTextField(
                value = viewModel.salary.value,
                onValueChange = { newValue ->
                    viewModel.salary.value = newValue
                },
                label = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Insert your year salary")
                        Spacer(Modifier.width(16.dp))
                        Box(
                            modifier = Modifier.border(
                                1.dp,
                                Color.Gray,
                                RoundedCornerShape(4.dp)
                            )
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = currency,
                                    style = MaterialTheme.typography.body2,
                                    modifier = Modifier.clickable {
                                        currency = if (currency == "USD") "CHF" else "USD"
                                    }
                                )
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = null,
                                    tint = MaterialTheme.colors.onBackground
                                )
                            }
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                ),
                modifier = Modifier.fillMaxWidth()
            )


            OutlinedTextField(value = viewModel.location.value,
                onValueChange = {
                    viewModel.location.value = it
                },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        textFiledSize = coordinates.size.toSize()
                    },
                label = { Text(text = "What location do you prefer the most?") },
                trailingIcon = {
                    Icon(icon, "", Modifier.clickable { expandedLocation = !expandedLocation })
                })

            DropdownMenu(
                expanded = expandedLocation,
                onDismissRequest = { expandedLocation = false },
                modifier = Modifier.width(with(LocalDensity.current) { textFiledSize.width.toDp() })
            ) {
                enumValues<JLocations>().forEach { location ->
                    DropdownMenuItem(onClick = {
                        expandedLocation = false
                        viewModel.location.value = location.value
                    }) {
                        Text(text = location.value)
                    }
                }

            }

            OutlinedTextField(value = viewModel.hobby.value,
                onValueChange = {
                    viewModel.hobby.value = it
                },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        textFiledSize = coordinates.size.toSize()
                    },
                label = { Text(text = "What are your favourite hobby's fields?") },
                trailingIcon = {
                    Icon(icon, "", Modifier.clickable { expandedHobby = !expandedHobby })
                })

            DropdownMenu(
                expanded = expandedHobby,
                onDismissRequest = { expandedHobby = false },
                modifier = Modifier.width(with(LocalDensity.current) { textFiledSize.width.toDp() })
            ) {
                enumValues<JHobbies>().forEach { hobbie ->
                    DropdownMenuItem(onClick = {
                        viewModel.hobby.value = hobbie.value
                        expandedHobby = false
                    }) {
                        Text(text = hobbie.value)
                    }
                }
            }

            OutlinedTextField(value = viewModel.occupation.value,
                onValueChange = {
                    viewModel.occupation.value = it
                },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        textFiledSize = coordinates.size.toSize()
                    },
                label = { Text(text = "What's your occupation Field?") },
                trailingIcon = {
                    Icon(icon, "", Modifier.clickable { expandedOccupation = !expandedOccupation })
                })

            DropdownMenu(
                expanded = expandedOccupation,
                onDismissRequest = { expandedOccupation = false },
                modifier = Modifier.width(with(LocalDensity.current) { textFiledSize.width.toDp() })
            ) {
                enumValues<JOccupation>().forEach { occupation ->
                    DropdownMenuItem(onClick = {
                        viewModel.occupation.value = occupation.value
                        expandedOccupation = false
                    }) {
                        Text(text = occupation.value)
                    }
                }
            }

            OutlinedTextField(value = viewModel.nightlife.value,
                onValueChange = {
                    viewModel.nightlife.value = it
                },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        textFiledSize = coordinates.size.toSize()
                    },
                label = { Text(text = "How much do you like to hang out?") },
                trailingIcon = {
                    Icon(icon, "", Modifier.clickable { expandedNight = !expandedNight })
                })

            DropdownMenu(
                expanded = expandedNight,
                onDismissRequest = { expandedNight = false },
                modifier = Modifier.width(with(LocalDensity.current) { textFiledSize.width.toDp() })
            ) {
                enumValues<JNightLifes>().forEach { nightlife ->
                    DropdownMenuItem(onClick = {
                        viewModel.nightlife.value = nightlife.value
                        expandedNight = false
                    }) {
                        Text(text = nightlife.value)
                    }
                }
            }

            OutlinedTextField(value = viewModel.temperature.value,
                onValueChange = {
                    viewModel.temperature.value = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        textFiledSize = coordinates.size.toSize()
                    },
                label = { Text(text = "Select your Favourite Temperature") },
                trailingIcon = {
                    Icon(icon,
                        "",
                        Modifier.clickable { expandedTemperature = !expandedTemperature })
                })

            DropdownMenu(
                expanded = expandedTemperature,
                onDismissRequest = { expandedTemperature = false },
                modifier = Modifier.width(with(LocalDensity.current) { textFiledSize.width.toDp() })
            ) {
                enumValues<JTemperature>().forEach { temperature ->
                    DropdownMenuItem(onClick = {
                        viewModel.temperature.value = temperature.value
                        expandedTemperature = false
                    }) {
                        Text(text = temperature.value)
                    }
                }
            }

            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(
                    onClick = {
                        scope.launch {
                            dataStore.saveSalary(viewModel.salary.value.toIntOrNull() ?: 0)
                            dataStore.saveOccupation(viewModel.occupation.value)
                            dataStore.saveLocation(viewModel.location.value)
                            dataStore.saveHobby(viewModel.hobby.value)
                            dataStore.saveNightlife(viewModel.nightlife.value)
                            dataStore.saveTemperature(viewModel.temperature.value)
                        }
                    }, modifier = Modifier.padding(top = 20.dp)
                ) {
                    Text(text = "Conferma")
                }
            }

        }
    }
}


