package com.example.apphub.matchPointsCounter.domain.scoring.model

data class GameState(
    val scores: MutableList<Int> = mutableListOf(0, 0)
)