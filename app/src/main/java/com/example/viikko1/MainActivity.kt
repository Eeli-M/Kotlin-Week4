package com.example.viikko1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.viikko1.navigation.ROUTE_CALENDAR
import com.example.viikko1.navigation.ROUTE_HOME
import com.example.viikko1.ui.theme.Viikko1Theme
import com.example.viikko1.view.CalendarScreen
import com.example.viikko1.view.HomeScreen
import com.example.viikko1.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    private val taskViewModel: TaskViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            Viikko1Theme {
                NavHost(
                    navController = navController,
                    startDestination = ROUTE_HOME
                ) {

                    composable(ROUTE_HOME) {
                        HomeScreen(
                            viewModel = taskViewModel,
                            onNavigateCalendar = { navController.navigate(ROUTE_CALENDAR) }
                        )
                    }
                    composable(ROUTE_CALENDAR) {
                        CalendarScreen(
                            viewModel = taskViewModel,
                            onNavigateHome = { navController.navigate(ROUTE_HOME) }
                        )
                    }
                }
            }
        }
    }
}
