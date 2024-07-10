package com.example.room_learning_course.ui

import androidx.lifecycle.ViewModel
import com.example.room_learning_course.data.CountriesDao
import com.example.room_learning_course.data.CountryState
import kotlinx.coroutines.flow.MutableStateFlow

class CountryViewModel(
    private val dao: CountriesDao
): ViewModel(

) {
    private val _state = MutableStateFlow(CountryState())
}