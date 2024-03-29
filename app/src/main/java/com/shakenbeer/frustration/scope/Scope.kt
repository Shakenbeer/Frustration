package com.shakenbeer.frustration.scope

import androidx.compose.runtime.Composable
import com.shakenbeer.frustration.shared.Chapter
import com.shakenbeer.frustration.shared.logMe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

private val scope = CoroutineScope(Job())

@Composable
fun Scope(onBackClick: () -> Unit) {
    Chapter(
        title = "3. Job has CoroutineContext and put there a reference to itself",
        onBackClick = onBackClick,
        onStartClick = ::jobAndScopeAreTheSame
    )
}

fun jobAndScopeAreTheSame() {
    logMe {
        log("parent scope = $scope")
        val job = scope.launch {
            log("scope = $this")
        }
        log("job = $job")
    }
}