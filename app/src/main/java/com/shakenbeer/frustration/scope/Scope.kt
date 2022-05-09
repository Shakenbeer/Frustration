package com.shakenbeer.frustration.scope

import androidx.compose.runtime.Composable
import com.shakenbeer.frustration.shared.ComposeLogger
import com.shakenbeer.frustration.shared.Chapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

private val logger = ComposeLogger()
private val scope = CoroutineScope(Job())

@Composable
fun Scope(onBackClick: () -> Unit) {
    Chapter(
        title = "3. Job has CoroutineContext and put there a reference to itself",
        lines = logger.lines,
        onBackClick = onBackClick,
        onStartClick = ::jobAndScopeAreTheSame
    )
}

fun jobAndScopeAreTheSame() {
    logger.log("parent scope = $scope")
    val job = scope.launch {
        logger.log("scope = $this")
    }
    logger.log("job = $job")
}