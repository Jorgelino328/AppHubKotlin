package com.example.apphub.todoList.ui

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.apphub.R
import com.example.apphub.todoList.domain.TodoManager

class TodoActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var editTask: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        // Inicialização dos componentes
        listView = findViewById(R.id.listTodo)
        editTask = findViewById(R.id.editTask)
        val btnAdd = findViewById<Button>(R.id.btnAddTodo)
        val btnVoltar = findViewById<Button>(R.id.btnVoltarTodo)

        // Configuração dos botões
        btnVoltar.setOnClickListener { finish() }

        btnAdd.setOnClickListener {
            val taskName = editTask.text.toString().trim()
            if (taskName.isNotEmpty()) {
                TodoManager.saveTask(this, taskName)
                editTask.text.clear()
                atualizar()
            } else {
                Toast.makeText(this, "Digite uma tarefa!", Toast.LENGTH_SHORT).show()
            }
        }

        atualizar()
    }

    private fun atualizar() {
        val tasks = TodoManager.getTasks(this)
        val txtEmpty = findViewById<TextView>(R.id.txtEmptyTodo)

        // Controle de visibilidade da lista vazia
        if (tasks.isEmpty()) {
            txtEmpty?.visibility = View.VISIBLE
            listView.visibility = View.GONE
        } else {
            txtEmpty?.visibility = View.GONE
            listView.visibility = View.VISIBLE
        }

        val adapter = object : ArrayAdapter<String>(this, R.layout.list_item_todo, tasks) {
            override fun getView(pos: Int, conv: View?, parent: ViewGroup): View {
                val v = conv ?: LayoutInflater.from(context)
                    .inflate(R.layout.list_item_todo, parent, false)

                val fullData = getItem(pos) ?: ""

                val parts = fullData.split("|")
                val taskName = parts[0]
                val isDone = if (parts.size > 1) parts[1].toBoolean() else false

                val txtTask = v.findViewById<TextView>(R.id.txtTodoTask)
                txtTask.text = taskName

                if (isDone) {
                    txtTask.setTextColor(context.getColor(R.color.purple_200))
                    txtTask.paintFlags = txtTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    txtTask.setTextColor(context.getColor(R.color.black))
                    txtTask.paintFlags = txtTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }

                v.setOnClickListener {
                    TodoManager.toggleTaskStatus(context, fullData)
                    atualizar()
                }

                v.findViewById<ImageButton>(R.id.btnDeleteTodo).setOnClickListener {
                    TodoManager.deleteTask(context, fullData)
                    atualizar()
                }

                return v
            }
        }

        listView.adapter = adapter
    }
}