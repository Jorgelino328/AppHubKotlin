package com.example.apphub.shoppingList

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.core.content.edit

class JsonData(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("shopping_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveList(list: List<ShoppingItem>) {
        val jsonString = gson.toJson(list)
        sharedPreferences.edit { putString("saved_list", jsonString) }
    }

    fun loadList(): MutableList<ShoppingItem> {
        val jsonString = sharedPreferences.getString("saved_list", null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<ShoppingItem>>() {}.type
        return gson.fromJson(jsonString, type)
    }
}