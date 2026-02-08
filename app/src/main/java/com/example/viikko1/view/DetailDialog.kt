package com.example.viikko1.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.viikko1.model.Task

@Composable
fun DetailDialog(
    task: Task,
    onClose: () -> Unit,
    onUpdate: (Task) -> Unit,
    onDelete: (Task) -> Unit
) {
    var title by remember { mutableStateOf(task.title) }
    var description by remember { mutableStateOf(task.description) }
    var dueDate by remember { mutableStateOf(task.dueDate)}

    AlertDialog(
        onDismissRequest = onClose,
        title = { Text("Edit Task") },
        text = {
            Column {
                TextField(value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") })
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") })
                TextField(
                    value = dueDate,
                    onValueChange = { dueDate = it },
                    label = { Text("Due date") }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                onUpdate(task.copy(title = title, description = description, dueDate = dueDate))
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            Row {
                Button(onClick = {
                    onDelete(task)
                }) {
                    Text("Delete")
                }

                Button(onClick = onClose) {
                    Text("Cancel")
                }
            }
        })
}