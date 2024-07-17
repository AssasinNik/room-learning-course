package com.example.room_learning_course.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.room_learning_course.data.CountriesEvent
import com.example.room_learning_course.data.CountryState

@Composable
fun AddCountryDialog(
    state : CountryState,
    onEvent : (CountriesEvent) -> Unit,
    modifier: Modifier = Modifier
){
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onEvent(CountriesEvent.HideDialog)},
        title = { Text(text = "Добавить страну")},
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.countryName,
                    onValueChange = {
                    onEvent(CountriesEvent.SaveCountryName(it)) 
                    },
                    placeholder = {
                        Text(text = "Название страны: ")
                    }
                )
                TextField(
                    value = state.countPeople,
                    onValueChange = {
                        onEvent(CountriesEvent.SaveCountPeople(it))
                    },
                    placeholder = {
                        Text(text = "Численность населения: ")
                    }
                )
                TextField(
                    value = state.presidentCountry,
                    onValueChange = {
                        onEvent(CountriesEvent.SaveCountryName(it))
                    },
                    placeholder = {
                        Text(text = "Президент страны: ")
                    }
                )
            }
        },
        buttons = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ){
                Button(onClick = {onEvent(CountriesEvent.SaveCountry)}) {
                    Text(text = "Добавить")
                }
            }
        }
    )
}