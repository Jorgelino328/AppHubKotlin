package com.example.apphub

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.apphub.calculadora.CalculadoraActivity
import com.example.apphub.matchPointsCounter.ui.modality.PickModality
import com.example.apphub.shoppingList.ListHubActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hub_main)

        val btnCalculadora = findViewById<Button>(R.id.btnCalculadora)
        val btnContador = findViewById<Button>(R.id.btnContador)
        val btnListaCompras = findViewById<Button>(R.id.btnListaCompras)

        btnCalculadora.setOnClickListener {
            val intent = Intent(this, CalculadoraActivity::class.java)
            startActivity(intent)
        }

        btnContador.setOnClickListener {
            val intent = Intent(this, PickModality::class.java)
            startActivity(intent)
        }

        btnListaCompras.setOnClickListener {
            val intent = Intent(this, ListHubActivity::class.java)
            startActivity(intent)
        }
    }
}