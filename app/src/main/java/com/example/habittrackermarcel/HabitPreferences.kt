package com.example.habittrackermarcel

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("habit_preferences")

class HabitPreferences(private val context: Context) {

    private val HABITS_KEY = stringSetPreferencesKey("habits")

    val habitListFlow: Flow<Set<String>> = context.dataStore.data
        .map { preferences ->
            preferences[HABITS_KEY] ?: emptySet()
        }

    suspend fun saveHabit(habit: String) {
        context.dataStore.edit { preferences ->
            val current = preferences[HABITS_KEY] ?: emptySet()
            preferences[HABITS_KEY] = current + habit
        }
    }

    suspend fun deleteHabit(habit: String) {
        context.dataStore.edit { preferences ->
            val current = preferences[HABITS_KEY] ?: emptySet()
            preferences[HABITS_KEY] = current - habit
        }
    }
}
