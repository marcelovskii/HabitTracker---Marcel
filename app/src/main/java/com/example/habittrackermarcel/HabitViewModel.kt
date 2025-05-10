package com.example.habittrackermarcel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HabitViewModel(private val habitPrefs: HabitPreferences) : ViewModel() {
    val habits: StateFlow<Set<String>> = habitPrefs.habitListFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptySet())

    fun addHabit(habit: String) {
        viewModelScope.launch {
            habitPrefs.saveHabit(habit)
        }
    }

    fun deleteHabit(habit: String) {
        viewModelScope.launch {
            habitPrefs.deleteHabit(habit)
        }
    }
}

class HabitViewModelFactory(private val habitPrefs: HabitPreferences) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HabitViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HabitViewModel(habitPrefs) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
