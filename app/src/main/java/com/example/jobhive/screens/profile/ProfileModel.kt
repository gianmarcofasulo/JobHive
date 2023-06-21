package com.example.jobhive.screens.profile

enum class JHobbies(val value: String) {
    SPORT("Sport"),
    POP("Pop-culture"),
    HIKING("Hiking"),
    NATURE("Nature")
}

enum class JNightLifes(val value: String) {
    A_LOT("A lot"),
    SOMETIMES("Sometimes"),
    QUIET_PLACES("I like quiet places")
}

enum class JLocations(val value: String) {
    MOUNTAIN("Mountain"),
    COASTLINE("Coastline"),
    FOREST("Forest"),
    CITY("City"),
    SNOWY("Snowy"),
    DESERT("Desert")
}

enum class JOccupation(val value: String) {
    ENGINEERING("Engineering"),
    ECONOMY("Economy"),
    HEALTH("Health"),
    HUMANISTIC_STUDIES("Humanistic studies"),
    ART("Art")
}

enum class JTemperature(val value: String) {
    HOT("Hot"),
    TEMPERATE("Temperate"),
    COLD("Cold")
}

data class Preferences(
    val salary: Int,
    val city: String,
    val hobby: String,
    val occupation: String,
    val hangoutTime: String,
    val temperature: String,
)