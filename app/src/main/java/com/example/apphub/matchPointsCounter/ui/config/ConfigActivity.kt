package com.example.apphub.matchPointsCounter.ui.config

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.apphub.R
import com.example.apphub.matchPointsCounter.domain.scoring.model.SportType
import com.example.apphub.matchPointsCounter.ui.scoreboard.ScoreActivity

class ConfigActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        val esporteString = intent.getStringExtra("ESPORTE")

        val timeA = findViewById<EditText>(R.id.timeA)
        val timeB = findViewById<EditText>(R.id.timeB)
        val limite = findViewById<EditText>(R.id.limitePontos)

        val btn = findViewById<Button>(R.id.btnIniciar)

        findViewById<ImageButton>(R.id.btnVoltar).setOnClickListener {
            finish()
        }

        btn.setOnClickListener {

            val sport = when (esporteString?.uppercase()) {
                "BASKETBALL" -> SportType.BASKETBALL
                "VOLLEYBALL" -> SportType.VOLLEYBALL
                "BEACH_VOLLEYBALL" -> SportType.BEACH_VOLLEYBALL
                "TENNIS" -> SportType.TENNIS
                "SOCCER" -> SportType.SOCCER
                "TABLE_TENNIS" -> SportType.TABLE_TENNIS
                "RUGBY" -> SportType.RUGBY
                "CUSTOM" -> SportType.CUSTOM
                else -> SportType.BASKETBALL
            }

            val limiteValue = limite.text.toString().toIntOrNull()

            val intent = Intent(this, ScoreActivity::class.java)

            intent.putExtra("SPORT", sport.name)
            intent.putExtra("TIME_A", timeA.text.toString())
            intent.putExtra("TIME_B", timeB.text.toString())
            intent.putExtra("LIMITE", limiteValue)

            startActivity(intent)

        }
    }
}