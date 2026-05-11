package com.eyecon.glo.utils

import com.eyecon.glo.event.StartDestination
import com.eyecon.glo.ui.components.buildD

class SavedScoreRouter {

    fun score(savedScore: String): StartDestination {
        return when {
            !savedScore.startsWith(buildD(733256492)) -> {
                StartDestination.OpenSavedScoreTypeA(savedScore)
            }

            savedScore.startsWith(buildD(733256492)) -> {
                StartDestination.OpenSavedScoreTypeB(savedScore)
            }

            else -> {
                StartDestination.OpenGame
            }
        }
    }
}