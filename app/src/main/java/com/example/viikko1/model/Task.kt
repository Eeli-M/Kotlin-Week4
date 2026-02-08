package com.example.viikko1.model

import java.time.LocalDate

data class Task (
    val id: Int,
    val title: String,
    val description: String,
    val priority: Int,
    val dueDate: String,
    val done: Boolean
)