@file:Suppress("BlockingMethodInNonBlockingContext")

package com.shakenbeer.frustration.builders.join

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shakenbeer.frustration.shared.Log
import com.shakenbeer.frustration.shared.logMe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

private lateinit var scope: CoroutineScope

@Composable
fun Join(onBackClick: () -> Unit) {
    scope = CoroutineScope(Job())
    Column(
        modifier = Modifier.padding(24.dp)
    ) {
        TextButton(onClick = {
            onBackClick()
            scope.cancel()
        }) {
            Text(text = "Back")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "5. Parent coroutine could suspend it's execution and wait for a child.",
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row {
            OutlinedButton(onClick = ::onDoNotWait) {
                Text(text = "Don't wait")
            }
            Spacer(modifier = Modifier.width(36.dp))
            OutlinedButton(onClick = ::onWait) {
                Text(text = "Wait")
            }
            Spacer(modifier = Modifier.width(36.dp))
            OutlinedButton(onClick = ::onParallel) {
                Text(text = "Parallel")
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Log()
    }
}

private fun onDoNotWait() {
    logMe {
        scope.launch {
            log("parent coroutine, start")

            launch {
                log("child coroutine, start")
                TimeUnit.MILLISECONDS.sleep(1000)
                log("child coroutine, end")
            }

            log("parent coroutine, end")
        }
    }
}

private fun onWait() {
    logMe {
        scope.launch {
            log("parent coroutine, start")

            val job = launch {
                log("child coroutine, start")
                TimeUnit.MILLISECONDS.sleep(1000)
                log("child coroutine, end")
            }

            log("parent coroutine, wait until child completes")
            job.join()

            log("parent coroutine, end")
        }
    }
}

private fun onParallel() {
    logMe {
        scope.launch {
            log("parent coroutine, start")

            val job = launch {
                TimeUnit.MILLISECONDS.sleep(1000)
            }

            val job2 = launch {
                TimeUnit.MILLISECONDS.sleep(1500)
            }

            log("parent coroutine, wait until children complete")
            job.join()
            job2.join()

            log("parent coroutine, end -> children coroutines took 1.5 sec:\n1. executed in parallel\n2. we know when BOTH are done")
        }
    }
}

