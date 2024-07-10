package com.example.room_learning_course.ui

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.room_learning_course.data.Countries
import com.example.room_learning_course.data.CountriesEvent
import com.example.room_learning_course.data.CountryState
import com.example.room_learning_course.data.SortClass

@Composable
fun MainScreen(
    state : CountryState,
    onEvent: (CountriesEvent) -> Unit
){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(CountriesEvent.ShowDialog) }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add country"
                )
            }
        },
        modifier = Modifier.background(
            Color.DarkGray
        )
    ) {padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item{
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    SortClass.entries.forEach { sortType ->
                        Row (
                            modifier = Modifier
                                .clickable {
                                    onEvent(CountriesEvent.SortCountries(sortType))
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            RadioButton(
                                selected = state.sortType == sortType,
                                onClick = {  onEvent(CountriesEvent.SortCountries(sortType))}
                            )
                            Text(text = sortType.name)
                        }
                    }
                }
            }
            items(state.countries){ countries ->
                Row (
                    modifier = Modifier.fillMaxWidth()
                ){
                    Column (Modifier.weight(1f)){
                        Text(
                            text = countries.countryName,
                            fontSize = 20.sp
                        )
                        Text(text = "Население: "+countries.countPeople,
                            fontSize = 16.sp)
                        Text(text = "Президент страны: "+countries.presidentCountry,
                            fontSize = 16.sp)
                    }
                    Icon(
                        imageVector = Icons.Default.Delete, 
                        contentDescription = "delete country",
                        modifier = Modifier.clickable {
                            onEvent(CountriesEvent.DeleteCountry(countries))
                        }
                    )
                }
            }
        }
    }
}