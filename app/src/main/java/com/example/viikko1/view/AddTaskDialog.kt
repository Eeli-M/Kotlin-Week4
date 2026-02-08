package com.example.viikko1.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikko1.model.Task
import com.example.viikko1.viewmodel.TaskViewModel

@Composable
fun AddTaskDialog(
    viewModel: TaskViewModel,
    onClose: () -> Unit,
    onUpdate: (Task) -> Unit
) {
    var title by remember { mutableStateOf("title") }
    var description by remember { mutableStateOf("description") }
    var dueDate by remember { mutableStateOf("dueDate") }
    var priority by remember { mutableStateOf("priority") }
    var task by remember {
        mutableStateOf(Task(id = 0, title = "", description = "", priority = 0, dueDate = "", done = false))
    }

    AlertDialog(
        onDismissRequest = onClose,
        title = { Text("Add Task") },
        text = {
            Column {
                TextField(value = title, onValueChange = { title = it }, label = { Text("Title") }, placeholder = { Text("Enter task title") })
                TextField(value = description, onValueChange = { description = it }, label = { Text("Description") }, placeholder = { Text("Enter task description") })
                TextField(value = dueDate, onValueChange = { dueDate = it }, label = { Text("Due date") }, placeholder = { Text("Enter task due date") })
            }
        },
        confirmButton = {
            Button(onClick = {
                task = Task(id = 0, title = title, description = description, priority = 0, dueDate = "", done = false)
                viewModel.addTask(task)
                onUpdate(task)
                onClose()
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = onClose) {
                Text("Cancel")
            }
        }
    )
}