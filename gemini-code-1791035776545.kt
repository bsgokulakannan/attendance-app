package com.example.attendanceapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StaffClassPinScreen(onBack: () -> Unit) {
    var assignedClass by remember { mutableStateOf("Computer Science - Sec A") }
    var enteredPin by remember { mutableStateOf("") }
    var statusMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Staff Portal: Attendance PIN", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Assigned Class: $assignedClass", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = enteredPin,
            onValueChange = { enteredPin = it },
            label = { Text("Enter Active Class PIN") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (enteredPin == "4321") {
                    statusMessage = "Attendance Session Opened Successfully!"
                } else {
                    statusMessage = "Invalid PIN for assigned class."
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Verify PIN & Open Attendance")
        }

        Spacer(modifier = Modifier.height(16.dp))
        if (statusMessage.isNotEmpty()) {
            Text(statusMessage, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(32.dp))
        TextButton(onClick = onBack) {
            Text("Log Out")
        }
    }
}