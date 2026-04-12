package com.caio.caiocardozo.partidabasquete_kotlin.ui.scoreboard

import androidx.lifecycle.*
import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.model.SportType
import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.rules.*

class ScoreViewModel : ViewModel() {

    private var rule: ScoringRule<*>? = null

    private val _state = MutableLiveData<Any>()
    val state: LiveData<Any> = _state

    fun startGame(sport: SportType) {
        rule = ScoringRules.getRule(sport)
        _state.value = rule!!.getInitialState()
    }

    fun addPoint(teamIndex: Int) {
        val current = _state.value ?: return

        val r = rule ?: return

        val newState = (r as ScoringRule<Any>).addPoint(current, teamIndex)
        _state.value = newState
    }

    fun addPoints(team: Int, points: Int) {
        repeat(points) {
            addPoint(team)
        }
    }
}