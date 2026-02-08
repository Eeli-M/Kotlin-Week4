package com.example.viikko1.model

import com.example.viikko1.model.Task
import java.time.LocalDate

val mockTasks = listOf(
    Task(1, "Task 1", "Desc 1", 1, LocalDate.now().plusDays(3).toString(), true),
    Task(2, "Task 2", "Desc 2", 2, LocalDate.now().plusDays(5).toString(), false),
    Task(3, "Task 3", "Desc 3", 3, LocalDate.now().plusDays(1).toString(), true),
    Task(4, "Task 4", "Desc 4", 4, LocalDate.now().toString(), false),
    Task(5, "Task 5", "Desc 5", 5, LocalDate.now().plusDays(2).toString(), false),
    Task(6, "Task 6", "Desc 6", 6, LocalDate.now().toString(), false)
)
