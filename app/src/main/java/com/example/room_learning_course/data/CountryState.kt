package com.example.room_learning_course.data

data class CountryState (
    val countries: List<Countries> = emptyList(),
    val countryName: String = "",
    val countPeople: Int = 0,
    val presidentCountry: String = "",
    val isAddingCountry : Boolean = false,
    val sortType: SortClass = SortClass.COUNTRY_NAME
)