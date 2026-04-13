package com.example.apphub.matchPointsCounter.domain.scoring.rules

import com.example.apphub.matchPointsCounter.domain.scoring.model.SportType
import com.example.apphub.matchPointsCounter.domain.scoring.rules.BasketballRule
import com.example.apphub.matchPointsCounter.domain.scoring.rules.ScoringRule
import com.example.apphub.matchPointsCounter.domain.scoring.rules.VolleyballRule

object ScoringRules {

    fun getRule(sport: SportType): ScoringRule<*> {
        return when (sport) {
            SportType.BASKETBALL -> BasketballRule()
            SportType.VOLLEYBALL -> VolleyballRule()
            SportType.BEACH_VOLLEYBALL -> BeachVolleyballRule()
            SportType.SOCCER -> SoccerRule()
            SportType.TENNIS -> TennisRule()
            SportType.TABLE_TENNIS -> TableTennisRule()
            SportType.RUGBY -> RugbyRule()

            else -> BasketballRule()
        }
    }
}