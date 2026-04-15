package com.example.apphub.shoppingList

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apphub.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ShoppingListActivity : AppCompatActivity() {
    private val shoppingList = mutableListOf<ShoppingItem>()
    private lateinit var data: JsonData
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list)

        data = JsonData(this)

        shoppingList.addAll(data.loadList())

        adapter = ItemAdapter(
            itemList = shoppingList,
            onItemSaved = { position, newText ->
                shoppingList[position].name = newText
                data.saveList(shoppingList)
                adapter.notifyItemChanged(position)
            },
            onItemDeleted = { position ->
                shoppingList.removeAt(position)
                data.saveList(shoppingList)
                adapter.notifyItemRemoved(position)
            },
            onItemChecked = { position, isChecked ->
                shoppingList[position].isChecked = isChecked
                data.saveList(shoppingList)
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
                data.saveList(shoppingList)
                adapter.notifyItemInserted(shoppingList.size - 1)
                inputNomeItem.text.clear()
            }
        }
    }
}