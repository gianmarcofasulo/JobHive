package com.example.jobhive.screens.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun createProfileViewModel(): ViewModelProvider.Factory {
    val factory = object : ViewModelProvider.Factory {
        override fun <ProfileViewModel : ViewModel> create(modelClass: Class<ProfileViewModel>): ProfileViewModel {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(
            ) as ProfileViewModel
        }
    }
    return factory
}

class ProfileViewModel: ViewModel() {
    var salary = mutableStateOf("")
    var nightlife = mutableStateOf("")
    var occupation = mutableStateOf("")
    var hobby = mutableStateOf("")
    var location = mutableStateOf("")
    var temperature = mutableStateOf("")

    fun setDataOnStart(preferences: Preferences){
        salary.value = preferences.salary.toString()
        temperature.value = preferences.temperature
        hobby.value = preferences.hobby
        occupation.value = preferences.occupation
        location.value = preferences.city
        nightlife.value= preferences.hangoutTime
    }
}
