package com.example.apphub.matchPointsCounter.ui.scoreboard.renderer

import android.view.*
import android.widget.*
import com.example.apphub.R
import com.example.apphub.matchPointsCounter.domain.scoring.model.GameState

class SoccerRenderer(
    private val teamAName: String,
    private val teamBName: String,
    private val onAddPoint: (Int) -> Unit
) : ScoreRenderer<GameState> {

    private lateinit var scoreA: TextView
    private lateinit var scoreB: TextView

    override fun createView(container: ViewGroup): View {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.view_soccer, container, false)

        val nameA = view.findViewById<TextView>(R.id.timeA)
        val nameB = view.findViewById<TextView>(R.id.timeB)

        scoreA = view.findViewById(R.id.scoreA)
        scoreB = view.findViewById(R.id.scoreB)

        nameA.text = teamAName
        nameB.text = teamBName

        view.findViewById<Button>(R.id.btnA).setOnClickListener {
            onAddPoint(0)
        }

        view.findViewById<Button>(R.id.btnB).setOnClickListener {
            onAddPoint(1)
        }

        return view
    }

    override fun bind(state: GameState) {
        scoreA.text = state.scores[0].toString()
        scoreB.text = state.scores[1].toString()
    }
}