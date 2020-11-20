package dev.filipebezerra.android.androiddevtrivia.data

data class Game(
    val questions: List<Question>,
    var currentQuestion: Question? = null,
)