package com.caio.caiocardozo.partidabasquete_kotlin.ui.scoreboard.renderer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.caio.caiocardozo.partidabasquete_kotlin.R
import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.model.GameState

class BasketballRenderer(
    private val teamAName: String,
    private val teamBName: String,
    private val onAddPoints: (Int, Int) -> Unit
) : ScoreRenderer<GameState> {

    private lateinit var scoreA: TextView
    private lateinit var scoreB: TextView

    override fun createView(container: ViewGroup): View {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.view_basketball, container, false)

        val nameA = view.findViewById<TextView>(R.id.timeA)
        val nameB = view.findViewById<TextView>(R.id.timeB)

        scoreA = view.findViewById(R.id.scoreA)
        scoreB = view.findViewById(R.id.scoreB)

        nameA.text = teamAName
        nameB.text = teamBName

        view.findViewById<Button>(R.id.btn3A).setOnClickListener {
            onAddPoints(0, 3)
        }

        view.findViewById<Button>(R.id.btn2A).setOnClickListener {
            onAddPoints(0, 2)
        }

        view.findViewById<Button>(R.id.btn1A).setOnClickListener {
            onAddPoints(0, 1)
        }

        view.findViewById<Button>(R.id.btn3B).setOnClickListener {
            onAddPoints(1, 3)
        }

        view.findViewById<Button>(R.id.btn2B).setOnClickListener {
            onAddPoints(1, 2)
        }

        view.findViewById<Button>(R.id.btn1B).setOnClickListener {
            onAddPoints(1, 1)
        }

        return view
    }

    override fun bind(state: GameState) {
        scoreA.text = state.scores[0].toString()
        scoreB.text = state.scores[1].toString()
    }
}