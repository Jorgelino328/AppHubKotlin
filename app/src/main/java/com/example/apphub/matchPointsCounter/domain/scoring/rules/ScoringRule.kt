package com.example.apphub.matchPointsCounter.domain.scoring.rules

interface ScoringRule<T> {
    fun getInitialState(): T
    fun addPoint(state: T, teamIndex: Int): T
    fun isGameOver(state: T): Boolean = false
}