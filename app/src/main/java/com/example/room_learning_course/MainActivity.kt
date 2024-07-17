package com.example.room_learning_course

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.room_learning_course.data.CountryDatabase
import com.example.room_learning_course.ui.CountryScreen
import com.example.room_learning_course.ui.CountryViewModel
import com.example.room_learning_course.ui.theme.RoomlearningcourseTheme

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            CountryDatabase::class.java,
            "countries.db"
        ).allowMainThreadQueries().build()
    }
    private val viewModel by viewModels<CountryViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CountryViewModel(db.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomlearningcourseTheme {
                val state by viewModel.state.collectAsState()
                CountryScreen(state = state, onEvent = viewModel::onEvent)
            }
        }
    }
}



