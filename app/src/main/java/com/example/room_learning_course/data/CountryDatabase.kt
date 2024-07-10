package com.example.room_learning_course.data

import androidx.room.Database
import androidx.room.RoomDatabase



@Database(
    entities = [Countries::class],
    exportSchema = false,
    version = 3
)
abstract class CountryDatabase: RoomDatabase() {
    abstract val dao: CountriesDao
}
