package com.example.jobhive.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreDataUser(private val context: Context) {

    companion object {
        private val Context.userPreferences: DataStore<Preferences> by preferencesDataStore("UserPreferences")
        val USER_EMAIL_KEY = stringPreferencesKey("user_mail")
        val USER_PASSWORD_KEY = stringPreferencesKey("user_password")
        val USER_SALARY_KEY = intPreferencesKey("user_salary")
        val USER_NIGHTLIFE_KEY = stringPreferencesKey("user_nightlife")
        val USER_OCCUPATION_KEY = stringPreferencesKey("user_occupation")
        val USER_HOBBY_KEY = stringPreferencesKey("user_hobby")
        val USER_LOCATION_KEY = stringPreferencesKey("user_location")
        val USER_TEMPERATURE_KEY = stringPreferencesKey("user_temperature")
    }

    // to get the email
    val getEmail: Flow<String?> = context.userPreferences.data
        .map { preferences ->
            preferences[USER_EMAIL_KEY] ?: ""
        }

    // password
    val getPassword: Flow<String?> = context.userPreferences.data
        .map { preferences ->
            preferences[USER_PASSWORD_KEY] ?: ""
        }

    // salary
    val getSalary: Flow<Int?> = context.userPreferences.data.map {
        it[USER_SALARY_KEY] ?: 0
    }

    // nightlife
    val getNightlife: Flow<String?> = context.userPreferences.data
        .map { preferences ->
            preferences[USER_NIGHTLIFE_KEY] ?: ""
        }

    // occupation
    val getOccupation: Flow<String?> = context.userPreferences.data
        .map { preferences ->
            preferences[USER_OCCUPATION_KEY] ?: ""
        }

    // hobby
    val getHobby: Flow<String?> = context.userPreferences.data
        .map { preferences ->
            preferences[USER_HOBBY_KEY] ?: ""
        }

    // location
    val getLocation: Flow<String?> = context.userPreferences.data
        .map { preferences ->
            preferences[USER_LOCATION_KEY] ?: ""
        }

    // temperature
    val getTemperature: Flow<String?> = context.userPreferences.data
        .map { preferences ->
            preferences[USER_TEMPERATURE_KEY] ?: ""
        }


    // to save the email
    suspend fun saveEmail(email: String) {
        context.userPreferences.edit { preferences ->
            preferences[USER_EMAIL_KEY] = email
        }
    }

    // password
    suspend fun savePassword(password: String) {
        context.userPreferences.edit { preferences ->
            preferences[USER_PASSWORD_KEY] = password
        }
    }

    // salary
    suspend fun saveSalary(salary: Int) {
        context.userPreferences.edit { preferences ->
            preferences[USER_SALARY_KEY] = salary
        }
    }

    // nightlife
    suspend fun saveNightlife(nightlife: String) {
        context.userPreferences.edit { preferences ->
            preferences[USER_NIGHTLIFE_KEY] = nightlife
        }
    }

    // occupation
    suspend fun saveOccupation(occupation: String) {
        context.userPreferences.edit { preferences ->
            preferences[USER_OCCUPATION_KEY] = occupation
        }
    }

    // hobby
    suspend fun saveHobby(hobby: String) {
        context.userPreferences.edit { preferences ->
            preferences[USER_HOBBY_KEY] = hobby
        }
    }

    // location
    suspend fun saveLocation(location: String) {
        context.userPreferences.edit { preferences ->
            preferences[USER_LOCATION_KEY] = location
        }
    }

    // temperature
    suspend fun saveTemperature(temperature: String) {
        context.userPreferences.edit { preferences ->
            preferences[USER_TEMPERATURE_KEY] = temperature
        }
    }

}