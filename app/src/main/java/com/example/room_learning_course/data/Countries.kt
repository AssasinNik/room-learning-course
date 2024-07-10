package com.example.room_learning_course.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Countries(
    val countryName: String,
    val countPeople: Int,
    val presidentCountry: String,
    @PrimaryKey(autoGenerate = true)
    val countryID: Int = 0
)
