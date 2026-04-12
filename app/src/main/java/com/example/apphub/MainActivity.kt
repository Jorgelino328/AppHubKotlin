package com.example.apphub

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.apphub.calculadora.CalculadoraActivity
// 1. Change the import to point to the modality screen
import com.example.apphub.matchPointsCounter.ui.modality.PickModality

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hub_main)

        val btnCalculadora = findViewById<Button>(R.id.btnCalculadora)
        val btnContador = findViewById<Button>(R.id.btnContador)

        btnCalculadora.setOnClickListener {
            val intent = Intent(this, CalculadoraActivity::class.java)
            startActivity(intent)
        }

        btnContador.setOnClickListener {
            val intent = Intent(this, PickModality::class.java)
            startActivity(intent)
        }
    }
}