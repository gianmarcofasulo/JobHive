package com.example.jobhive.screens.usamap

import android.util.Log
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jobhive.datastore.USAcities
import com.example.jobhive.datastore.USAcityList
import com.example.jobhive.screens.profile.Preferences

fun createUsaMapViewModel(): ViewModelProvider.Factory {
    val factory = object : ViewModelProvider.Factory {
        override fun <UsaMapViewModel : ViewModel> create(modelClass: Class<UsaMapViewModel>): UsaMapViewModel {
            @Suppress("UNCHECKED_CAST")
            return UsaMapViewModel(
            ) as UsaMapViewModel
        }
    }
    return factory
}

class UsaMapViewModel: ViewModel() {
    private val _stateMap = mutableStateMapOf<USAcities, Int>()
    var stateMap: Map<USAcities, Int> = _stateMap

    fun scoreCalculator(preferences: Preferences) {
        val salary = preferences.salary
        val temperature = preferences.temperature
        val hobby = preferences.hobby
        val occupation = preferences.occupation
        val location = preferences.city
        val nightlife = preferences.hangoutTime

        Log.d("scoreCalculator", preferences.toString())
        val mappaTmp = mutableMapOf<USAcities, Int>()
        USAcityList.forEach { selectedCity ->
            Log.d("scoreCalculator", selectedCity.toString())

            var score = 0
            val name = USAcities.fromString(selectedCity.name)

            if (selectedCity.mediumLifeCost > salary) {
                score += 1
            } else if (selectedCity.mediumLifeCost < salary) {
                score += 2
            }

            if (selectedCity.weather == temperature) {
                score += 1
            }

            when (selectedCity.criminalityRisk) {
                "High" -> score += 1
                "Low" -> score += 2
            }

            if (selectedCity.occupation == occupation) {
                score += 1
            }

            if (selectedCity.hobbies.contains(hobby)) {
                score += 1
            }

            if (selectedCity.location == location) {
                score += 1
            }

            if (selectedCity.nightlife == nightlife) {
                score += 1
            }

            // adjust score to be between 1 and 3
            score = when {
                score < 1 -> 1
                score > 3 -> 3
                else -> score
            }

            mappaTmp[name] = score
            Log.d("scoreCalculator", "$name $score")
        }
        _stateMap.clear()
        _stateMap.putAll(mappaTmp)
    }

    fun getScoreBy(city: USAcities):Int{
        return if (stateMap.isNotEmpty())
            stateMap[city]?: 0
        else 0
    }

}
