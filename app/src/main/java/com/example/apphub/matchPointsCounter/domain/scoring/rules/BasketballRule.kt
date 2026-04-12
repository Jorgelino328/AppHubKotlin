package com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.rules

import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.model.GameState

class BasketballRule : ScoringRule<GameState> {

    override fun getInitialState(): GameState {
        return GameState()
    }

    override fun addPoint(state: GameState, teamIndex: Int): GameState {
        val newScores = state.scores.toMutableList()
        newScores[teamIndex]++

        return state.copy(scores = newScores)
    }
}