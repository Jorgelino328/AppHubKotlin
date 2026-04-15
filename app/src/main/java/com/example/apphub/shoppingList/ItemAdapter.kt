package com.example.apphub.shoppingList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apphub.R

class ItemAdapter(
    private val itemList: MutableList<String>,
    private val onItemDeleted: (Int) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textNomeItem)
        val deleteButton: ImageButton = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.novo_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.textView.text = currentItem

        holder.deleteButton.setOnClickListener {
            val currentPosition = holder.adapterPosition

            if (currentPosition != RecyclerView.NO_POSITION) {
                onItemDeleted(currentPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}