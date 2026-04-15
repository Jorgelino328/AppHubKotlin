package com.example.apphub.shoppingList

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apphub.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListHubActivity : AppCompatActivity() {
    private val masterLists = mutableListOf<ShoppingListMeta>()
    private lateinit var jsonData: JsonData
    private lateinit var adapter: ListHubAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_shopping_list)

        val textLista = findViewById<TextView>(R.id.textLista)
        textLista.text = "Minhas listas de compras"

        val inputNomeItem = findViewById<EditText>(R.id.nomeItem)
        inputNomeItem.hint = "Nome da nova lista"

        val btnAdd = findViewById<FloatingActionButton>(R.id.btnAdd)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewItens)

        jsonData = JsonData(this)
        masterLists.addAll(jsonData.loadAllLists())

        adapter = ListHubAdapter(
            hubLists = masterLists,
            onListClicked = { clickedList ->
                val intent = Intent(this, ShoppingListActivity::class.java)
                intent.putExtra("LIST_ID", clickedList.id)
                intent.putExtra("LIST_NAME", clickedList.name)
                startActivity(intent)
            },
            onItemSaved = { position, newText ->
                masterLists[position].name = newText
                jsonData.saveAllLists(masterLists)
                adapter.notifyItemChanged(position)
            },
            onItemDeleted = { position ->
                val listToDelete = masterLists[position]
                jsonData.deleteListCompletely(listToDelete.id)

                masterLists.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
        )

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        btnAdd.setOnClickListener {
            val text = inputNomeItem.text.toString()
            if (text.isNotBlank()) {
                masterLists.add(ShoppingListMeta(name = text))
                jsonData.saveAllLists(masterLists)
                adapter.notifyItemInserted(masterLists.size - 1)
                inputNomeItem.text.clear()
            }
        }
    }
}