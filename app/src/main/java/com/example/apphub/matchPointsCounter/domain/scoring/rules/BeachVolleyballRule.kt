package com.example.apphub.matchPointsCounter.domain.scoring.rules


import com.example.apphub.matchPointsCounter.domain.scoring.model.GameState
import kotlin.math.abs


class BeachVolleyballRule : ScoringRule<GameState> {

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

        return (a >= 21 || b >= 21) && abs(a - b) >= 2
    }
}