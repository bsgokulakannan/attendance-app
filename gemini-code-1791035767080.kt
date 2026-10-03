package com.example.attendanceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.attendanceapp.ui.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var currentScreen by remember { mutableStateOf("login") }
                    var userRole by remember { mutableStateOf("") }

                    when (currentScreen) {
                        "login" -> LoginScreen(
                            onLoginSuccess = { role ->
                                userRole = role
                                currentScreen = when (role) {
                                    "Student" -> "student_dashboard"
                                    "Staff" -> "staff_pin"
                                    "Admin" -> "admin_panel"
                                    else -> "login"
                                }
                            }
                        )
                        "student_dashboard" -> StudentDashboard(onLogout = { currentScreen = "login" })
                        "staff_pin" -> StaffClassPinScreen(onBack = { currentScreen = "login" })
                        "admin_panel" -> AdminPanel(onBack = { currentScreen = "login" })
                    }
                }
            }
        }
    }
}