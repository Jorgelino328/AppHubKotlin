package com.example.apphub.matchPointsCounter.ui.modality
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.apphub.matchPointsCounter.domain.scoring.model.SportType
import com.example.apphub.R
import com.example.apphub.matchPointsCounter.ui.config.ConfigActivity

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

//        setupComingSoon(R.id.cardFutebol)
//        setupComingSoon(R.id.cardVoleiDeAreia)
//        setupComingSoon(R.id.cardTenisDeMesa)
//        setupComingSoon(R.id.cardRugby)
//        setupComingSoon(R.id.cardLivre)

        findViewById<LinearLayout>(R.id.cardFutebol).setOnClickListener {
            navegar(SportType.SOCCER)
        }

        findViewById<LinearLayout>(R.id.cardBasquete).setOnClickListener {
            navegar(SportType.BASKETBALL)
        }

        findViewById<LinearLayout>(R.id.cardVolei).setOnClickListener {
            navegar(SportType.VOLLEYBALL)
        }

        findViewById<LinearLayout>(R.id.cardVoleiDeAreia).setOnClickListener {
            navegar(SportType.BEACH_VOLLEYBALL)
        }

        findViewById<LinearLayout>(R.id.cardTenis).setOnClickListener {
            navegar(SportType.TENNIS)
        }

        findViewById<LinearLayout>(R.id.cardTenisDeMesa).setOnClickListener {
            navegar(SportType.TABLE_TENNIS)
        }

        findViewById<LinearLayout>(R.id.cardRugby).setOnClickListener {
            navegar(SportType.RUGBY)
        }

        findViewById<LinearLayout>(R.id.cardLivre).setOnClickListener {
            navegar(SportType.CUSTOM)
        }
    }
    private fun navegar(esporte: SportType) {
        val intent = Intent(this, ConfigActivity::class.java)
        intent.putExtra("ESPORTE", esporte.name)
        startActivity(intent)
    }
}