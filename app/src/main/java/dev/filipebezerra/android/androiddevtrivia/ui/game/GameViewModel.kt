package dev.filipebezerra.android.androiddevtrivia.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.filipebezerra.android.androiddevtrivia.R
import dev.filipebezerra.android.androiddevtrivia.data.Answer
import dev.filipebezerra.android.androiddevtrivia.data.Game
import dev.filipebezerra.android.androiddevtrivia.data.Question
import dev.filipebezerra.android.androiddevtrivia.ui.util.event.Event
import dev.filipebezerra.android.androiddevtrivia.ui.util.ext.postEvent

class GameViewModel : ViewModel() {

    private val _game = MutableLiveData<Game>()
    val game: LiveData<Game>
        get() = _game

    private val _navigateToGameWon = MutableLiveData<Event<Any>>()
    val navigateToGameWon: LiveData<Event<Any>>
        get() = _navigateToGameWon

    private val _navigateToGameOver = MutableLiveData<Event<Any>>()
    val navigateToGameOver: LiveData<Event<Any>>
        get() = _navigateToGameOver

    val selectedAnswerIndex = MutableLiveData<Int>()

    init {
        initiateGame()
    }

    private fun initiateGame() {
        _game.value = Game(
            questions = listOf(
                Question(
                    questionText = "What is Android Jetpack?",
                    answers = listOf(
                        Answer("all of these"),
                        Answer("tools"),
                        Answer("documentation"),
                        Answer("libraries"),
                    ),
                    correctAnswer = Answer("all of these"),
                ),
                Question(
                    questionText = "Base class for Layout?",
                    answers = listOf(
                        Answer("ViewGroup"),
                        Answer("ViewSet"),
                        Answer("ViewCollection"),
                        Answer("ViewRoot"),
                    ),
                    correctAnswer = Answer("ViewGroup"),
                ),
                Question(
                    questionText = "Layout for complex Screens?",
                    answers = listOf(
                        Answer("ConstraintLayout"),
                        Answer("GridLayout"),
                        Answer("LinearLayout"),
                        Answer("FrameLayout"),
                    ),
                    correctAnswer = Answer("ConstraintLayout"),
                ),
                Question(
                    questionText = "Pushing structured data into a Layout?",
                    answers = listOf(
                        Answer("Data Binding"),
                        Answer("Data Pushing"),
                        Answer("Set Text"),
                        Answer("OnClick"),
                    ),
                    correctAnswer = Answer("Data Binding"),
                ),
                Question(
                    questionText = "Inflate layout in fragments?",
                    answers = listOf(
                        Answer("onCreateView"),
                        Answer("onViewCreated"),
                        Answer("onCreateLayout"),
                        Answer("onInflateLayout"),
                    ),
                    correctAnswer = Answer("onCreateView"),
                ),
                Question(
                    questionText = "Build system for Android?",
                    answers = listOf(
                        Answer("Gradle"),
                        Answer("Graddle"),
                        Answer("Grodle"),
                        Answer("Groyle"),
                    ),
                    correctAnswer = Answer("Gradle"),
                ),
                Question(
                    questionText = "Android vector format?",
                    answers = listOf(
                        Answer("VectorDrawable"),
                        Answer("AndroidVectorDrawable"),
                        Answer("DrawableVector"),
                        Answer("AndroidVector"),
                    ),
                    correctAnswer = Answer("VectorDrawable"),
                ),
                Question(
                    questionText = "Android Navigation Component?",
                    answers = listOf(
                        Answer("NavController"),
                        Answer("NavCentral"),
                        Answer("NavMaster"),
                        Answer("NavSwitcher"),
                    ),
                    correctAnswer = Answer("NavController"),
                ),
                Question(
                    questionText = "Registers app with launcher?",
                    answers = listOf(
                        Answer("intent-filter"),
                        Answer("app-registry"),
                        Answer("launcher-registry"),
                        Answer("app-launcher"),
                    ),
                    correctAnswer = Answer("intent-filter"),
                ),
                Question(
                    questionText = "Mark a layout for Data Binding?",
                    answers = listOf(
                        Answer("<layout>"),
                        Answer("<binding>"),
                        Answer("<data-binding>"),
                        Answer("<dbinding>"),
                    ),
                    correctAnswer = Answer("<layout>"),
                ),
            )
        ).apply {
            currentQuestion = questions.first()
        }
    }

    fun submitAnswer() {
        selectedAnswerIndex.value?.let { selectedAnswerIndex ->
            _game.value?.currentQuestion?.let { question ->
                var answerIndex = 0
                when (selectedAnswerIndex) {
                    R.id.secondAnswerRadioButton -> answerIndex = 1
                    R.id.thirdAnswerRadioButton -> answerIndex = 2
                    R.id.fourthAnswerRadioButton -> answerIndex = 3
                }

                val correctAnswerText = question.correctAnswer.answerText
                val selectedAnswer = question.answers[answerIndex]

                if (correctAnswerText == selectedAnswer.answerText) {
                    _navigateToGameWon.postEvent(true)
                } else {
                    _navigateToGameOver.postEvent(true)
                }
            }
        }
    }
}