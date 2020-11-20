package dev.filipebezerra.android.androiddevtrivia.ui.util.ext

import androidx.lifecycle.MutableLiveData
import dev.filipebezerra.android.androiddevtrivia.ui.util.event.Event

fun <T> MutableLiveData<Event<T>>.postEvent(content: T) {
    postValue(Event(content))
}