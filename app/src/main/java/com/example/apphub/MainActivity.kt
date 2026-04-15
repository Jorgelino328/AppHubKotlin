package com.example.apphub

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.apphub.calculadora.CalculadoraActivity
import com.example.apphub.matchPointsCounter.ui.modality.PickModality
import com.example.apphub.shoppingList.ListHubActivity
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        val darkMode = prefs.getBoolean("dark_mode", false)

        AppCompatDelegate.setDefaultNightMode(
            if (darkMode) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hub_main)

        val switchDarkMode = findViewById<SwitchMaterial>(R.id.switchDarkMode)
        switchDarkMode.isChecked = darkMode

        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("dark_mode", isChecked).apply()
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
        }

        val btnCalculadora = findViewById<Button>(R.id.btnCalculadora)
        val btnContador = findViewById<Button>(R.id.btnContador)
        val btnListaCompras = findViewById<Button>(R.id.btnListaCompras)

        btnCalculadora.setOnClickListener {
            startActivity(Intent(this, CalculadoraActivity::class.java))
        }

        btnContador.setOnClickListener {
            startActivity(Intent(this, PickModality::class.java))
        }

        btnListaCompras.setOnClickListener {
            startActivity(Intent(this, ListHubActivity::class.java))
        }
    }
}