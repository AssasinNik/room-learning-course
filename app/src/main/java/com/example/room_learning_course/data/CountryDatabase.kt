package com.example.room_learning_course.data

import androidx.room.Database


@Database(
    entities = [Countries::class],
    version = 1
)
abstract class CountryDatabase {
    abstract val dao: CountriesDao
}