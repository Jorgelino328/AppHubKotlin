package com.example.apphub.todoList.domain

import android.content.Context

object TodoManager {
    private const val PREFS_NAME = "todo_prefs"
    private const val KEY_TASKS = "tasks_list"

    fun saveTask(context: Context, taskName: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val tasks = getTasks(context).toMutableList()
        tasks.add(0, "${taskName}|false")
        prefs.edit().putStringSet(KEY_TASKS, tasks.toSet()).apply()
    }

    fun getTasks(context: Context): List<String> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getStringSet(KEY_TASKS, emptySet())?.toList() ?: emptyList()
    }

    fun toggleTaskStatus(context: Context, fullTaskString: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val tasks = getTasks(context).toMutableList()

        val index = tasks.indexOf(fullTaskString)
        if (index != -1) {
            val parts = fullTaskString.split("|")
            val name = parts[0]
            val currentStatus = parts[1].toBoolean()
            tasks[index] = "$name|${!currentStatus}"
            prefs.edit().putStringSet(KEY_TASKS, tasks.toSet()).apply()
        }
    }

    fun deleteTask(context: Context, fullTaskString: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val tasks = getTasks(context).toMutableList()
        if (tasks.remove(fullTaskString)) {
            prefs.edit().putStringSet(KEY_TASKS, tasks.toSet()).apply()
        }
    }
}