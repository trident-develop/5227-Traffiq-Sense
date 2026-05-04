package com.eyecon.glo.model

data class Question(
    val text: String,
    val options: List<String>,
    val correctIndex: Int,
)

data class Test(
    val id: String,
    val topicId: String,
    val title: String,
    val questions: List<Question>,
)
