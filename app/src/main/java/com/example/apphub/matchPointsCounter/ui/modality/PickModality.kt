package com.caio.caiocardozo.partidabasquete_kotlin.ui.modality

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.caio.caiocardozo.partidabasquete_kotlin.R
import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.model.SportType
import com.caio.caiocardozo.partidabasquete_kotlin.ui.config.ConfigActivity

class PickModality : AppCompatActivity() {

    private fun setupComingSoon(id: Int) {
        val view = findViewById<LinearLayout>(id)

        view.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Aviso")
                .setMessage("Essa modalidade estará disponível em breve!")
                .setPositiveButton("OK", null)
                .show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick_modality)

        setupComingSoon(R.id.cardFutebol)
        setupComingSoon(R.id.cardVoleiDeAreia)
        setupComingSoon(R.id.cardTenisDeMesa)
        setupComingSoon(R.id.cardSinuca)
        setupComingSoon(R.id.cardLivre)

        findViewById<LinearLayout>(R.id.cardBasquete).setOnClickListener {
            navegar(SportType.BASKETBALL)
        }

        findViewById<LinearLayout>(R.id.cardVolei).setOnClickListener {
            navegar(SportType.VOLLEYBALL)
        }

        findViewById<LinearLayout>(R.id.cardTenis).setOnClickListener {
            navegar(SportType.TENNIS)
        }
    }
    private fun navegar(esporte: SportType) {
        val intent = Intent(this, ConfigActivity::class.java)
        intent.putExtra("ESPORTE", esporte.name)
        startActivity(intent)
    }
}