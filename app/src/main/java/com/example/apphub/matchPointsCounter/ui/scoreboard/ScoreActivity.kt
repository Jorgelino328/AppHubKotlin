package com.caio.caiocardozo.partidabasquete_kotlin.ui.scoreboard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.caio.caiocardozo.partidabasquete_kotlin.R
import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.model.GameState
import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.model.SportType
import com.caio.caiocardozo.partidabasquete_kotlin.ui.modality.PickModality
import com.caio.caiocardozo.partidabasquete_kotlin.ui.scoreboard.renderer.*

class ScoreActivity : AppCompatActivity() {

    private lateinit var container: FrameLayout
    private lateinit var viewModel: ScoreViewModel

    private var renderer: ScoreRenderer<Any>? = null

    private var limite: Int? = null
    private var gameFinished = false

    private var teamAName = "Time A"
    private var teamBName = "Time B"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        container = findViewById(R.id.container)

        val btnFinish = findViewById<Button>(R.id.btnFinishGame)
        btnFinish.setOnClickListener {
            showFinishDialog()
        }

        // 🔥 SPORT
        val sportString = intent.getStringExtra("SPORT")
        val sport = try {
            SportType.valueOf(sportString ?: "")
        } catch (e: Exception) {
            SportType.BASKETBALL
        }

        teamAName = intent.getStringExtra("TIME_A")
            .takeIf { !it.isNullOrBlank() } ?: "Time A"

        teamBName = intent.getStringExtra("TIME_B")
            .takeIf { !it.isNullOrBlank() } ?: "Time B"

        val limiteExtra = intent.getSerializableExtra("LIMITE") as? Int

        limite = when {
            limiteExtra != null -> limiteExtra
            sport == SportType.VOLLEYBALL -> 25
            sport == SportType.TENNIS -> 40
            else -> null
        }

        viewModel = ViewModelProvider(this)[ScoreViewModel::class.java]

        setupRenderer(sport)
        viewModel.startGame(sport)

        viewModel.state.observe(this) { state ->
            renderer?.bind(state)
            checkGameEnd(state)
        }
    }

    private fun setupRenderer(sport: SportType) {
        renderer = when (sport) {

            SportType.BASKETBALL -> BasketballRenderer(
                teamAName,
                teamBName
            ) { team, points ->
                viewModel.addPoints(team, points)
            } as ScoreRenderer<Any>

            SportType.VOLLEYBALL -> VolleyballRenderer(
                teamAName,
                teamBName
            ) {
                viewModel.addPoint(it)
            } as ScoreRenderer<Any>

            SportType.TENNIS -> TennisRenderer(
                teamAName,
                teamBName
            ) { team, points ->
                viewModel.addPoints(team, points)
            } as ScoreRenderer<Any>

            else -> BasketballRenderer(
                teamAName,
                teamBName
            ) { team, points ->
                viewModel.addPoints(team, points)
            } as ScoreRenderer<Any>
        }

        val view = renderer!!.createView(container)
        container.removeAllViews()
        container.addView(view)
    }

    private fun checkGameEnd(state: Any) {
        val gameState = state as? GameState ?: return
        val limit = limite ?: return

        gameState.scores.forEachIndexed { index, score ->
            if (score >= limit && !gameFinished) {
                gameFinished = true
                showWinnerDialog(index)
            }
        }
    }

    private fun showWinnerDialog(winnerIndex: Int) {
        val winnerName = if (winnerIndex == 0) teamAName else teamBName

        AlertDialog.Builder(this)
            .setTitle("Fim de jogo")
            .setMessage("$winnerName venceu a partida!")
            .setCancelable(false)
            .setPositiveButton("Nova partida") { _, _ ->
                goToHome()
            }
            .show()
    }

    private fun showFinishDialog() {
        AlertDialog.Builder(this)
            .setTitle("Encerrar partida")
            .setMessage("Tem certeza que deseja finalizar?")
            .setPositiveButton("Sim") { _, _ ->
                goToHome()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun goToHome() {
        val intent = Intent(this, PickModality::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}