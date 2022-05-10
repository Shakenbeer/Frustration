package com.shakenbeer.frustration.intro

import androidx.compose.runtime.Composable
import com.shakenbeer.frustration.shared.ComposeLogger
import com.shakenbeer.frustration.shared.Chapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private val logger = ComposeLogger()
private val scope = CoroutineScope(Job())

@Composable
fun Intro(onBackClick: () -> Unit) {
    Chapter(
        "1. Coroutine could be started in the main thread.",
        logger.lines, onBackClick, ::startCoroutineInTheMainThread
    )
}

fun startCoroutineInTheMainThread() {
    with (logger) {
        start()
        log("start coroutine")
        scope.launch {
            log("inside coroutine start suspend function \"delay(1 second)\"")
            delay(1000L)
            log("inside coroutine after \"delay\" executed")
        }
        log("execute code directly after coroutine started -> thread is not blocked")
    }
}