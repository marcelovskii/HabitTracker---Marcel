package com.example.habittrackermarcel.ui


import com.example.habittrackermarcel.HabitViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(habitViewModel: HabitViewModel) {
    val habitList by habitViewModel.habits.collectAsState()
    var newHabit by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Habit Tracker by Marcel") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = newHabit,
                onValueChange = { newHabit = it },
                label = { Text("New habit") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (newHabit.isNotBlank()) {
                        habitViewModel.addHabit(newHabit)
                        newHabit = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add habit")
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(habitList.toList()) { habit ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Text(
                            text = habit,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

