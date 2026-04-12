package com.example.apphub.matchPointsCounter.domain.scoring.rules

import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.model.SportType
import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.rules.BasketballRule
import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.rules.ScoringRule
import com.caio.caiocardozo.partidabasquete_kotlin.domain.scoring.rules.VolleyballRule

object ScoringRules {

    fun getRule(sport: SportType): ScoringRule<*> {
        return when (sport) {
            SportType.BASKETBALL -> BasketballRule()
            SportType.VOLLEYBALL -> VolleyballRule()
            SportType.TENNIS -> TennisRule()

            else -> BasketballRule()
        }
    }
}