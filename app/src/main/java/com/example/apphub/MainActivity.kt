package com.example.apphub

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.apphub.calculadora.CalculadoraActivity
import com.example.apphub.matchPointsCounter.ui.main.MatchPointsActivity // Double check this import matches your exact starting activity for the scoreboard!

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hub_main)

        // 1. Find the buttons from the XML layout
        val btnCalculadora = findViewById<Button>(R.id.btnCalculadora)
        val btnContador = findViewById<Button>(R.id.btnContador)

        // 2. Set up click listeners with Intents
        btnCalculadora.setOnClickListener {
            val intent = Intent(this, CalculadoraActivity::class.java)
            startActivity(intent)
        }

        btnContador.setOnClickListener {
            val intent = Intent(this, MatchPointsActivity::class.java)
            startActivity(intent)
        }
    }
}