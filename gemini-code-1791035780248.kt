package com.example.attendanceapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StudentDashboard(onLogout: () -> Unit) {
    var classesAttended by remember { mutableStateOf("45") }
    var totalClasses by remember { mutableStateOf("60") }

    val attended = classesAttended.toIntOrNull() ?: 0
    val total = totalClasses.toIntOrNull() ?: 1
    val percentage = if (total > 0) (attended.toFloat() / total.toFloat()) * 100f else 0f

    // 75% Calculator Logic
    val targetPercentage = 75.0f
    val calculatorResult = remember(attended, total) {
        if (percentage >= targetPercentage) {
            val canSkip = ((100 * attended - 75 * total) / 75)
            "You can safely skip up to $canSkip upcoming classes while maintaining 75%."
        } else {
            val needAttend = ((3 * total - 4 * attended)).coerceAtLeast(0)
            "You need to attend the next $needAttend classes consecutively to reach 75%."
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Student Attendance Dashboard", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(24.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Overall Attendance: %.1f%%".format(percentage), style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { percentage / 100f },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = classesAttended,
            onValueChange = { classesAttended = it },
            label = { Text("Classes Attended") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = totalClasses,
            onValueChange = { totalClasses = it },
            label = { Text("Total Classes Conducted") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("75% Attendance Advisor", style = MaterialTheme.typography.titleSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text(calculatorResult, style = MaterialTheme.typography.bodyMedium)
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        TextButton(onClick = onLogout) {
            Text("Log Out")
        }
    }
}