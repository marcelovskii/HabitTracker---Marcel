package com.example.habittrackermarcel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.habittrackermarcel.ui.MainScreen
import com.example.habittrackermarcel.ui.theme.HabitTrackerMarcelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val habitPrefs = remember { HabitPreferences(context) }
            val viewModel: HabitViewModel = viewModel(
                factory = HabitViewModelFactory(habitPrefs)
            )

            HabitTrackerMarcelTheme {
                MainScreen(habitViewModel = viewModel)
            }
        }
    }
}
