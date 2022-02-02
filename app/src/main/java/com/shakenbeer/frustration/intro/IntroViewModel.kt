package com.shakenbeer.frustration.intro

import androidx.lifecycle.ViewModel
import com.shakenbeer.frustration.log.ComposeLogger
import com.shakenbeer.frustration.log.log
import kotlinx.coroutines.*

class IntroViewModel : ViewModel() {

    private val logger = ComposeLogger()
    private val scope = CoroutineScope(Job())

    private val currentThreadName: String
        get() = Thread.currentThread().name

    val lines = logger.lines

    fun startCoroutineInTheMainThread() {
        with (logger) {
            start()
            log("start coroutine in $currentThreadName")
            scope.launch {
                log("inside coroutine start suspend function \"delay(1 second)\" in $currentThreadName")
                delay(1000L)
                log("inside coroutine after \"delay\" executed in $currentThreadName")
            }
            log("execute code directly after coroutine started in $currentThreadName -> thread is not blocked")
        }
    }
}