package com.eyecon.glo.storage

import android.content.Context
import android.content.SharedPreferences
import com.eyecon.glo.model.TestResult
import org.json.JSONObject

class TestResultStorage(context: Context) {

    private val prefs: SharedPreferences =
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun getResult(testId: String): TestResult? {
        val raw = prefs.getString(testId, null) ?: return null
        return runCatching { fromJson(testId, JSONObject(raw)) }.getOrNull()
    }

    fun getAllResults(): Map<String, TestResult> {
        val all = mutableMapOf<String, TestResult>()
        prefs.all.forEach { (key, value) ->
            if (value is String) {
                runCatching { all[key] = fromJson(key, JSONObject(value)) }
            }
        }
        return all
    }

    fun saveResult(
        testId: String,
        testTitle: String,
        score: Int,
        correct: Int,
        total: Int,
        percentage: Int,
        completedAt: Long,
    ): TestResult {
        val previous = getResult(testId)
        val bestScore = maxOf(score, previous?.bestScore ?: 0)
        val bestCorrect = maxOf(correct, previous?.bestCorrect ?: 0)
        val bestPercentage = maxOf(percentage, previous?.bestPercentage ?: 0)

        val updated = TestResult(
            testId = testId,
            testTitle = testTitle,
            lastScore = score,
            lastCorrect = correct,
            totalQuestions = total,
            lastPercentage = percentage,
            lastCompletedAt = completedAt,
            bestScore = bestScore,
            bestCorrect = bestCorrect,
            bestPercentage = bestPercentage,
        )

        prefs.edit().putString(testId, toJson(updated).toString()).apply()
        return updated
    }

    private fun fromJson(testId: String, obj: JSONObject): TestResult = TestResult(
        testId = testId,
        testTitle = obj.optString(KEY_TITLE),
        lastScore = obj.optInt(KEY_LAST_SCORE),
        lastCorrect = obj.optInt(KEY_LAST_CORRECT),
        totalQuestions = obj.optInt(KEY_TOTAL, 20),
        lastPercentage = obj.optInt(KEY_LAST_PERCENT),
        lastCompletedAt = obj.optLong(KEY_LAST_AT),
        bestScore = obj.optInt(KEY_BEST_SCORE),
        bestCorrect = obj.optInt(KEY_BEST_CORRECT),
        bestPercentage = obj.optInt(KEY_BEST_PERCENT),
    )

    private fun toJson(result: TestResult): JSONObject = JSONObject().apply {
        put(KEY_TITLE, result.testTitle)
        put(KEY_LAST_SCORE, result.lastScore)
        put(KEY_LAST_CORRECT, result.lastCorrect)
        put(KEY_TOTAL, result.totalQuestions)
        put(KEY_LAST_PERCENT, result.lastPercentage)
        put(KEY_LAST_AT, result.lastCompletedAt)
        put(KEY_BEST_SCORE, result.bestScore)
        put(KEY_BEST_CORRECT, result.bestCorrect)
        put(KEY_BEST_PERCENT, result.bestPercentage)
    }

    companion object {
        private const val PREFS_NAME = "traffiq_results"
        private const val KEY_TITLE = "title"
        private const val KEY_LAST_SCORE = "last_score"
        private const val KEY_LAST_CORRECT = "last_correct"
        private const val KEY_TOTAL = "total"
        private const val KEY_LAST_PERCENT = "last_percent"
        private const val KEY_LAST_AT = "last_at"
        private const val KEY_BEST_SCORE = "best_score"
        private const val KEY_BEST_CORRECT = "best_correct"
        private const val KEY_BEST_PERCENT = "best_percent"
    }
}
