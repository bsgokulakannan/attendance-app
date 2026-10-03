package com.example.attendanceapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AdminPanel(onBack: () -> Unit) {
    var staffEmail by remember { mutableStateOf("") }
    var assignedSubject by remember { mutableStateOf("") }
    var classPin by remember { mutableStateOf("") }
    var successMsg by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Admin Management Panel", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = staffEmail,
            onValueChange = { staffEmail = it },
            label = { Text("Staff Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = assignedSubject,
            onValueChange = { assignedSubject = it },
            label = { Text("Assign Class / Subject") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = classPin,
            onValueChange = { classPin = it },
            label = { Text("Set Unique Class PIN") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (staffEmail.isNotBlank() && classPin.isNotBlank()) {
                    successMsg = "Successfully assigned $assignedSubject to $staffEmail with PIN $classPin."
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Staff Assignment & PIN")
        }

        Spacer(modifier = Modifier.height(16.dp))
        if (successMsg.isNotEmpty()) {
            Text(successMsg, color = MaterialTheme.colorScheme.primary)
        }

        Spacer(modifier = Modifier.weight(1f))
        TextButton(onClick = onBack) {
            Text("Log Out")
        }
    }
}