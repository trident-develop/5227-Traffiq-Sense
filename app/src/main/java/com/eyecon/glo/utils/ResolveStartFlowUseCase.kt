package com.eyecon.glo.utils

import com.eyecon.glo.data.ScoreBuilder
import com.eyecon.glo.event.StartDestination
import com.eyecon.glo.storage.GameRepo

class ResolveStartFlowUseCase(
    private val gameRepo: GameRepo,
    private val paramsCollector: ScoreParamsCollector,
    private val linkBuilder: ScoreBuilder,
    private val savedScoreRouter: SavedScoreRouter
) {

    suspend operator fun invoke(): StartDestination {
        val savedScore = gameRepo.getSavedScore()

        return if (savedScore.isNullOrBlank()) {
//            Log.d("MYTAG", "SAVED LINK IS EMPTY -> BUILD NEW LINK")

            val params = paramsCollector.collect()
            val builtLink = linkBuilder.build(params)

            StartDestination.BuiltScore(builtLink)

        } else {
//            Log.d("MYTAG", "SAVED LINK EXISTS -> $savedScore")

            savedScoreRouter.score(savedScore)
        }
    }
}