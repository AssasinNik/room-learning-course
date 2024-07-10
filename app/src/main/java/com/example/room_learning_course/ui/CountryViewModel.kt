package com.example.room_learning_course.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.room_learning_course.data.Countries
import com.example.room_learning_course.data.CountriesDao
import com.example.room_learning_course.data.CountriesEvent
import com.example.room_learning_course.data.CountryState
import com.example.room_learning_course.data.SortClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class CountryViewModel(
    private val dao: CountriesDao
): ViewModel(

) {
    private val _sortType = MutableStateFlow(SortClass.COUNTRY_NAME)
    private val _countries = _sortType
        .flatMapLatest { sortType ->
            when(sortType){
                SortClass.COUNTRY_NAME -> dao.getCountriesOrderedByName()
                SortClass.PRESIDENT_NAME -> dao.getCountriesOrderedByPresident()
                SortClass.COUNT_PEOPLE -> dao.getCountriesOrderedByPeople()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(CountryState())

    val state = combine(_sortType, _countries, _state){sortType, countries, state ->
        state.copy(
            countries = countries,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CountryState())


    fun onEvent(event: CountriesEvent){
        when(event){
            is CountriesEvent.DeleteCountry -> {
                viewModelScope.launch {
                    dao.deleteCountry(event.country)
                }
            }
            CountriesEvent.HideDialog -> {
                _state.update {
                    it.copy(
                        isAddingCountry = false
                    )
                }
            }
            is CountriesEvent.SaveCountPeople -> {
                _state.update {
                    it.copy(
                        countPeople = event.countPeople
                    )
                }
            }
            CountriesEvent.SaveCountry -> {
                val countryName =state.value.countryName
                val countPeople = state.value.countPeople
                val presidentCountry = state.value.presidentCountry

                if(countryName.isBlank() || countPeople.equals(null) || presidentCountry.isBlank()){
                    return
                }

                val country =  Countries(
                    countryName = countryName,
                    countPeople = countPeople,
                    presidentCountry = presidentCountry
                )
                viewModelScope.launch {
                    dao.insertCountry(country)
                }
                _state.update {
                    it.copy(
                        isAddingCountry = false,
                        countryName = "",
                        countPeople = 0,
                        presidentCountry = ""
                    )
                }
            }
            is CountriesEvent.SaveCountryName -> _state.update {
                it.copy(
                    countryName = event.countryName
                )
            }
            is CountriesEvent.SavePresidentCountry -> _state.update {
                it.copy(
                    presidentCountry = event.presidentCountry
                )
            }
            CountriesEvent.ShowDialog -> {
                _state.update {
                    it.copy(
                        isAddingCountry = true
                    )
                }
            }
            is CountriesEvent.SortCountries -> {
                _sortType.value = event.sortType
            }
        }
    }
}