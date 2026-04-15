package com.example.apphub.shoppingList

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apphub.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ShoppingListActivity : AppCompatActivity() {

    private val shoppingList = mutableListOf<String>()

    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list)

        val inputNomeItem = findViewById<EditText>(R.id.nomeItem)
        val btnAdd = findViewById<FloatingActionButton>(R.id.btnAdd)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewItens)

        adapter = ItemAdapter(shoppingList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        btnAdd.setOnClickListener {
            val newItemText = inputNomeItem.text.toString()

            if (newItemText.isNotBlank()) {

                shoppingList.add(newItemText)

                adapter.notifyItemInserted(shoppingList.size - 1)

                recyclerView.scrollToPosition(shoppingList.size - 1)

                inputNomeItem.text.clear()
            }
        }
    }
}