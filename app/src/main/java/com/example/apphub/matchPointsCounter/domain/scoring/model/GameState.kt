package com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.model

data class GameState(
    val scores: MutableList<Int> = mutableListOf(0, 0)
)