package dev.filipebezerra.android.androiddevtrivia.data

data class Question(
    val questionText: String,
    val answers: List<Answer>,
    val correctAnswer: Answer,
)