package com.example.apphub.matchPointsCounter.domain.scoring.rules

import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.model.GameState
import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.rules.ScoringRule

class TennisRule : ScoringRule<GameState> {

    override fun getInitialState(): GameState {
        return GameState()
    }

    override fun addPoint(state: GameState, teamIndex: Int): GameState {
        val newScores = state.scores.toMutableList()
        newScores[teamIndex]++

        return state.copy(scores = newScores)
    }
}