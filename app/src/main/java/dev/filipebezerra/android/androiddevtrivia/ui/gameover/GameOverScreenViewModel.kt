package dev.filipebezerra.android.androiddevtrivia.ui.gameover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.filipebezerra.android.androiddevtrivia.ui.util.event.Event
import dev.filipebezerra.android.androiddevtrivia.ui.util.ext.postEvent

class GameOverScreenViewModel : ViewModel() {

    private val _navigateToGame = MutableLiveData<Event<Any>>()
    val navigateToGame: LiveData<Event<Any>>
        get() = _navigateToGame

    fun playAgain() = _navigateToGame.postEvent(true)
}