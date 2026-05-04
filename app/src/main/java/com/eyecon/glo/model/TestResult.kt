package com.eyecon.glo.model

data class TestResult(
    val testId: String,
    val testTitle: String,
    val lastScore: Int,
    val lastCorrect: Int,
    val totalQuestions: Int,
    val lastPercentage: Int,
    val lastCompletedAt: Long,
    val bestScore: Int,
    val bestCorrect: Int,
    val bestPercentage: Int,
)
