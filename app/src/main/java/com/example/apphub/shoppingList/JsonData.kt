package com.example.apphub.shoppingList

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.core.content.edit

class JsonData(context: Context) {
    private val prefs = context.getSharedPreferences("shopping_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun loadAllLists(): MutableList<ShoppingListMeta> {
        val json = prefs.getString("master_list_index", null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<ShoppingListMeta>>() {}.type
        return gson.fromJson(json, type)
    }

    fun saveAllLists(lists: List<ShoppingListMeta>) {
        val json = gson.toJson(lists)
        prefs.edit { putString("master_list_index", json) }
    }

    fun deleteListCompletely(listId: String) {
        prefs.edit { remove("list_items_$listId") }

        val currentLists = loadAllLists()
        currentLists.removeAll { it.id == listId }
        saveAllLists(currentLists)
    }

    fun loadItems(listId: String): MutableList<ShoppingItem> {
        val json = prefs.getString("list_items_$listId", null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<ShoppingItem>>() {}.type
        return gson.fromJson(json, type)
    }

    fun saveItems(listId: String, items: List<ShoppingItem>) {
        val json = gson.toJson(items)
        prefs.edit { putString("list_items_$listId", json) }
    }
}