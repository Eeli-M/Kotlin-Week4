package com.example.viikko1.viewmodel

import androidx.lifecycle.ViewModel
import com.example.viikko1.model.Task
import com.example.viikko1.model.mockTasks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TaskViewModel : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private val _selectedTask = MutableStateFlow<Task?>(null)
    val selectedTask: StateFlow<Task?> = _selectedTask

    init {
        _tasks.value = mockTasks
    }

    fun addTask(task: Task) {
        _tasks.value += task
    }

    fun toggleDone(id: Int) {
        _tasks.value = _tasks.value.map {
            if(it.id == id) it.copy(done = !it.done) else it
        }
    }

    fun removeTask(id: Int) {
        _tasks.value = _tasks.value.filter { it.id != id }
    }

    fun filterByDone(done: Boolean) {
        _tasks.value = _tasks.value.filter { it.done == done }
    }

    fun sortByDueDate() {
        _tasks.value = _tasks.value.sortedBy { it.dueDate }
    }

    fun selectTask(task: Task) {
        _selectedTask.value = task
    }

    fun updateTask(updated: Task) {
        _tasks.value = _tasks.value.map {
            if(it.id == updated.id) updated else it
        }
        _selectedTask.value = null
    }

    fun closeDialog() {
        _selectedTask.value = null
    }
}