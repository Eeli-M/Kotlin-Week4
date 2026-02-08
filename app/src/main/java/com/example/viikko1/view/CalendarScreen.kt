package com.example.viikko1.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viikko1.model.Task
import com.example.viikko1.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    viewModel: TaskViewModel,
    onTaskClick: () -> Unit = {},
    onNavigateHome: () -> Unit
) {
    val tasks by viewModel.tasks.collectAsState()
    val selectedTask by viewModel.selectedTask.collectAsState()
    val grouped = tasks.groupBy { it.dueDate }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calendar") },
                navigationIcon = {
                    IconButton(onClick = onNavigateHome) {
                        Icon(Icons.Filled.List, contentDescription = "Task List")
                    }
                }
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            grouped.forEach { (date, tasksOfDay) ->
                item {
                    Text(
                        text = date,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                }

                items(tasksOfDay) { task ->
                    CalendarTaskCard(
                        task = task,
                        onTaskClick = { viewModel.selectTask(task) }
                    )
                }
            }
        }

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
}

@Composable
fun CalendarTaskCard(
    task: Task,
    onTaskClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
            .clickable { onTaskClick() }
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(task.title, style = MaterialTheme.typography.titleMedium)
            if (task.description.isNotBlank()) {
                Text(task.description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}