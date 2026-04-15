package com.example.apphub.shoppingList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apphub.R

class ListHubAdapter(
    private val hubLists: MutableList<ShoppingListMeta>,
    private val onListClicked: (ShoppingListMeta) -> Unit,
    private val onItemSaved: (Int, String) -> Unit,
    private val onItemDeleted: (Int) -> Unit
) : RecyclerView.Adapter<ListHubAdapter.HubViewHolder>() {

    class HubViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textNomeItem)
        val editTextBox: EditText = view.findViewById(R.id.editNomeItem)
        val editButton: ImageButton = view.findViewById(R.id.btnEdit)
        val saveButton: ImageButton = view.findViewById(R.id.btnSave)
        val deleteButton: ImageButton = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HubViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.novo_item, parent, false)
        return HubViewHolder(view)
    }

    override fun onBindViewHolder(holder: HubViewHolder, position: Int) {
        val currentList = hubLists[position]

        holder.textView.visibility = View.VISIBLE
        holder.editButton.visibility = View.VISIBLE
        holder.editTextBox.visibility = View.GONE
        holder.saveButton.visibility = View.GONE

        holder.itemView.findViewById<View>(R.id.checkBoxItem)?.visibility = View.GONE

        holder.textView.text = currentList.name

        holder.textView.setOnClickListener {
            onListClicked(currentList)
        }

        holder.editButton.setOnClickListener {
            holder.textView.visibility = View.GONE
            holder.editButton.visibility = View.GONE
            holder.editTextBox.visibility = View.VISIBLE
            holder.saveButton.visibility = View.VISIBLE
            holder.editTextBox.setText(currentList.name)
            holder.editTextBox.setSelection(currentList.name.length)
            holder.editTextBox.requestFocus()
        }

        holder.saveButton.setOnClickListener {
            val currentPosition = holder.adapterPosition
            val newText = holder.editTextBox.text.toString()
            if (currentPosition != RecyclerView.NO_POSITION && newText.isNotBlank()) {
                onItemSaved(currentPosition, newText)
            }
        }

        holder.deleteButton.setOnClickListener {
            val currentPosition = holder.adapterPosition
            if (currentPosition != RecyclerView.NO_POSITION) {
                onItemDeleted(currentPosition)
            }
        }
    }

    override fun getItemCount(): Int = hubLists.size
}