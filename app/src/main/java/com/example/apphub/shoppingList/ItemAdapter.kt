package com.example.apphub.shoppingList

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apphub.R

class ItemAdapter(
    private val itemList: MutableList<ShoppingItem>,
    private val onItemSaved: (Int, String) -> Unit,
    private val onItemDeleted: (Int) -> Unit,
    private val onItemChecked: (Int, Boolean) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val checkBox: CheckBox = view.findViewById(R.id.checkBoxItem)
        val textView: TextView = view.findViewById(R.id.textNomeItem)
        val editTextBox: EditText = view.findViewById(R.id.editNomeItem)
        val editButton: ImageButton = view.findViewById(R.id.btnEdit)
        val saveButton: ImageButton = view.findViewById(R.id.btnSave)
        val deleteButton: ImageButton = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.novo_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]

        holder.textView.visibility = View.VISIBLE
        holder.editButton.visibility = View.VISIBLE
        holder.editTextBox.visibility = View.GONE
        holder.saveButton.visibility = View.GONE
        holder.textView.text = currentItem.name
        holder.checkBox.setOnCheckedChangeListener(null)
        holder.checkBox.isChecked = currentItem.isChecked

        if (currentItem.isChecked) {
            holder.textView.paintFlags = holder.textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            holder.textView.paintFlags = holder.textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            val currentPosition = holder.adapterPosition
            if (currentPosition != RecyclerView.NO_POSITION) {
                onItemChecked(currentPosition, isChecked)
            }
        }

        holder.editButton.setOnClickListener {
            holder.textView.visibility = View.GONE
            holder.editButton.visibility = View.GONE
            holder.editTextBox.visibility = View.VISIBLE
            holder.saveButton.visibility = View.VISIBLE
            holder.editTextBox.setText(currentItem.name)
            holder.editTextBox.setSelection(currentItem.name.length)
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

    override fun getItemCount(): Int {
        return itemList.size
    }
}