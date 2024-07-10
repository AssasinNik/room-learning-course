package com.example.room_learning_course.data

sealed interface CountriesEvent {
    object SaveCountry: CountriesEvent
    data class SaveCountryName(val countryName : String): CountriesEvent
    data class SaveCountPeople(val countPeople : Int): CountriesEvent
    data class SavePresidentCountry(val presidentCountry : String): CountriesEvent
    object ShowDialog: CountriesEvent
    object HideDialog: CountriesEvent
    data class SortCountries(val sortType: SortClass): CountriesEvent
    data class DeleteCountry(val country: Countries): CountriesEvent
}