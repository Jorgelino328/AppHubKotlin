package com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.rules

import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.model.GameState
import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.rules.ScoringRule


class VolleyballRule : ScoringRule<GameState> {

    override fun getInitialState(): GameState {
        return GameState()
    }

    override fun addPoint(state: GameState, teamIndex: Int): GameState {
        val newScores = state.scores.toMutableList()
        newScores[teamIndex]++

        return state.copy(scores = newScores)
    }

    override fun isGameOver(state: GameState): Boolean {
        val a = state.scores[0]
        val b = state.scores[1]

        return (a >= 25 || b >= 25) && kotlin.math.abs(a - b) >= 2
    }
}