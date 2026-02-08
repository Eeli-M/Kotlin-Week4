package com.example.viikko1.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikko1.model.Task
import com.example.viikko1.viewmodel.TaskViewModel
import java.time.LocalDate


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: TaskViewModel,
    onTaskClick: (Int) -> Unit = {},
    onAddClick: () -> Unit = {},
    onNavigateCalendar: () -> Unit = {}
) {
    val tasks by viewModel.tasks.collectAsState()
    val selectedTask by viewModel.selectedTask.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)) {

            TopAppBar(
                title = { Text("Task List", style = MaterialTheme.typography.headlineMedium) },
                actions = {
                    IconButton(onClick = onNavigateCalendar) {
                        Icon(
                            imageVector = Icons.Default.CalendarMonth,
                            contentDescription = "Show calendar"
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { viewModel.filterByDone(false) }) {
                    Text("Active tasks")
                }
                Button(onClick = { viewModel.filterByDone(true) }) {
                    Text("Done tasks")
                }
                Button(onClick = { viewModel.sortByDueDate() }) {
                    Text("Sort by date")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(tasks) { task ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable { viewModel.selectTask(task) }) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(task.title, style = MaterialTheme.typography.headlineSmall)
                                Text(task.description)
                            }
                            Checkbox(
                                checked = task.done,
                                onCheckedChange = { viewModel.toggleDone(task.id) }
                            )
                        }
                    }
                }
            }

            Button(
                onClick = { showAddDialog = true},
                modifier = Modifier.padding(8.dp),
            ) {
                Text("+")
            }

            if (selectedTask != null) {
                DetailDialog(
                    task = selectedTask!!,
                    onClose = { viewModel.closeDialog() },
                    onUpdate = { viewModel.updateTask(it) },
                    onDelete = { taskToDelete ->
                        viewModel.removeTask(taskToDelete.id)
                        viewModel.closeDialog()
                    })
            }
            if (showAddDialog) {
                AddTaskDialog(
                    viewModel = viewModel,
                    onClose = { showAddDialog = false },
                    onUpdate = {
                        showAddDialog = false
                    }
                )
            }

        }
    }
}
