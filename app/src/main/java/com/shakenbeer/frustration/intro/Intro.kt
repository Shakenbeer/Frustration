package com.shakenbeer.frustration.intro

import androidx.compose.runtime.Composable
import com.shakenbeer.frustration.shared.ComposeLogger
import com.shakenbeer.frustration.shared.Start
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private val logger = ComposeLogger()
private val scope = CoroutineScope(Job())
private val currentThreadName: String
    get() = Thread.currentThread().name

@Composable
fun Intro() {
    Start(
        "1. Coroutine could be started in the main thread.",
        logger.lines, ::startCoroutineInTheMainThread
    )
}

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