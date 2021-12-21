package com.shakenbeer.frustration.intro

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shakenbeer.frustration.log.ComposeLogger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.format.DateTimeFormatter.ISO_LOCAL_TIME

class IntroViewModel : ViewModel() {

    private val logger = ComposeLogger()
    private val scope = CoroutineScope(Job())

    val lines = logger.lines

    fun startCoroutineInTheMainThread() {
        logger.start()
        log("We start coroutine in \"${Thread.currentThread().name}\"")
        scope.launch {
            log("We are inside coroutine and start suspend function \"delay(1 second)\" in \"${Thread.currentThread().name}\"")
            delay(1000L)
            log("We are inside coroutine after \"delay\" executed, and current thread is still \"${Thread.currentThread().name}\"")
        }
        log("We execute some code directly after coroutine is started in \"${Thread.currentThread().name}\" -> thread is not blocked")
    }

    private fun log(msg: String) {
        logger.add("${ISO_LOCAL_TIME.format(LocalTime.now())} $msg")
    }

}