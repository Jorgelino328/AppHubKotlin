package com.example.apphub.todoList.domain

data class TodoItem(
    val task: String,
    var isCompleted: Boolean = false
)