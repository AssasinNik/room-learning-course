package com.example.room_learning_course.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface CountriesDao {

    @Upsert
    fun insertCountry( country: Countries)

    @Delete
    fun deleteCountry(country: Countries)

    @Query("SELECT * FROM countries ORDER BY countryName ASC")
    fun getCountriesOrderedByName(): Flow<List<Countries>>

    @Query("SELECT * FROM countries ORDER BY countPeople ASC")
    fun getCountriesOrderedByPeople(): Flow<List<Countries>>

    @Query("SELECT * FROM countries ORDER BY presidentCountry ASC")
    fun getCountriesOrderedByPresident(): Flow<List<Countries>>
}