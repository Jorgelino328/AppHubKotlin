package com.example.apphub.shoppingList

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apphub.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ShoppingListActivity : AppCompatActivity() {
    private val shoppingList = mutableListOf<ShoppingItem>()
    private lateinit var jsonData: JsonData
    private lateinit var adapter: ItemAdapter

    private lateinit var currentListId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list)

        currentListId = intent.getStringExtra("LIST_ID") ?: "default_list"
        val currentListName = intent.getStringExtra("LIST_NAME") ?: "Shopping List"

        val textLista = findViewById<TextView>(R.id.textLista)
        textLista.text = currentListName

        jsonData = JsonData(this)

        shoppingList.addAll(jsonData.loadItems(currentListId))

        adapter = ItemAdapter(
            itemList = shoppingList,
            onItemSaved = { position, newText ->
                shoppingList[position].name = newText
                jsonData.saveItems(currentListId, shoppingList) // Updated
                adapter.notifyItemChanged(position)
            },
            onItemDeleted = { position ->
                shoppingList.removeAt(position)
                jsonData.saveItems(currentListId, shoppingList) // Updated
                adapter.notifyItemRemoved(position)
            },
            onItemChecked = { position, isChecked ->
                shoppingList[position].isChecked = isChecked
                jsonData.saveItems(currentListId, shoppingList) // Updated
                adapter.notifyItemChanged(position)
            }
        )

        val inputNomeItem = findViewById<EditText>(R.id.nomeItem)
        val btnAdd = findViewById<FloatingActionButton>(R.id.btnAdd)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewItens)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        btnAdd.setOnClickListener {
            val text = inputNomeItem.text.toString()
            if (text.isNotBlank()) {
                shoppingList.add(ShoppingItem(name = text))
                jsonData.saveItems(currentListId, shoppingList) // Updated
                adapter.notifyItemInserted(shoppingList.size - 1)
                inputNomeItem.text.clear()
            }
        }
    }
}