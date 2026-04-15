package com.example.apphub

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.apphub.calculadora.CalculadoraActivity
import com.example.apphub.matchPointsCounter.ui.modality.PickModality
import com.example.apphub.todoList.ui.TodoActivity // Import da sua nova tela

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hub_main)

        val btnCalculadora = findViewById<Button>(R.id.btnCalculadora)
        val btnContador = findViewById<Button>(R.id.btnContador)
        val btnTodo = findViewById<Button>(R.id.btnTodo)

        btnCalculadora.setOnClickListener {
            val intent = Intent(this, CalculadoraActivity::class.java)
            startActivity(intent)
        }

        btnContador.setOnClickListener {
            val intent = Intent(this, PickModality::class.java)
            startActivity(intent)
        }

        // Lógica para abrir o Todo-List
        btnTodo.setOnClickListener {
            val intent = Intent(this, TodoActivity::class.java)
            startActivity(intent)
        }
    }
}