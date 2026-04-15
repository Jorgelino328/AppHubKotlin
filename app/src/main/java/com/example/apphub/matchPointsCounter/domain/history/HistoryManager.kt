package com.example.apphub.matchPointsCounter.domain.history

import android.content.Context

object HistoryManager {
    private const val PREFS_NAME = "game_history"
    private const val KEY_HISTORY = "history_list"

    fun saveMatch(context: Context, result: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val history = getHistory(context).toMutableList()
        history.add(0, result)
        prefs.edit().putStringSet(KEY_HISTORY, history.toSet()).apply()
    }

    fun getHistory(context: Context): List<String> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getStringSet(KEY_HISTORY, emptySet())?.toList() ?: emptyList()
    }

    fun deleteMatch(context: Context, matchResult: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val history = getHistory(context).toMutableList()

        if (history.remove(matchResult)) {
            prefs.edit().putStringSet(KEY_HISTORY, history.toSet()).apply()
        }
    }

    fun clearHistory(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().remove(KEY_HISTORY).apply()
    }
}