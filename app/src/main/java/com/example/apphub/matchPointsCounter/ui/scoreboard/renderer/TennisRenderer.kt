package com.caio.caiocardozo.partidabasquete_kotlin.ui.scoreboard.renderer

import android.view.*
import android.widget.*
import com.caio.caiocardozo.partidabasquete_kotlin.R
import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.model.GameState

class TennisRenderer(
    private val teamAName: String,
    private val teamBName: String,
    private val onAddPoints: (Int, Int) -> Unit
) : ScoreRenderer<GameState> {

    private lateinit var scoreA: TextView
    private lateinit var scoreB: TextView

    override fun createView(container: ViewGroup): View {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.view_tennis, container, false)

        val nameA = view.findViewById<TextView>(R.id.timeA)
        val nameB = view.findViewById<TextView>(R.id.timeB)

        scoreA = view.findViewById(R.id.scoreA)
        scoreB = view.findViewById(R.id.scoreB)

        nameA.text = teamAName
        nameB.text = teamBName

        view.findViewById<Button>(R.id.btn15A).setOnClickListener {
            onAddPoints(0, 15)
        }

        view.findViewById<Button>(R.id.btn10A).setOnClickListener {
            onAddPoints(0, 10)
        }

        // 🔥 TIME B
        view.findViewById<Button>(R.id.btn15B).setOnClickListener {
            onAddPoints(1, 15)
        }

        view.findViewById<Button>(R.id.btn10B).setOnClickListener {
            onAddPoints(1, 10)
        }

        return view
    }

    override fun bind(state: GameState) {
        scoreA.text = state.scores[0].toString()
        scoreB.text = state.scores[1].toString()
    }
}