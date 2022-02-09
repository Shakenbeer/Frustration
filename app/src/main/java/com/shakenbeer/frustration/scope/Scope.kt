package com.shakenbeer.frustration.scope

import androidx.compose.runtime.Composable
import com.shakenbeer.frustration.shared.ComposeLogger
import com.shakenbeer.frustration.shared.Start
import com.shakenbeer.frustration.shared.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Scope() {
    val logger = ComposeLogger()
    val scope = CoroutineScope(Job())
    Start(
        "3. Parent scope.",
        logger.lines
    ) {
        fun currentThreadName() = Thread.currentThread().name
        with (logger) {
            start()
            log("start coroutine in ${currentThreadName()}")
            scope.launch {
                log("inside coroutine start suspend function \"delay(1 second)\" in ${currentThreadName()}")
                delay(1000L)
                log("inside coroutine after \"delay\" executed in ${currentThreadName()}")
            }
            log("execute code directly after coroutine started in ${currentThreadName()} -> thread is not blocked")
        }
    }
}