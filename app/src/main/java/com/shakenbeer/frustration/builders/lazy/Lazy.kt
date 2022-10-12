package com.shakenbeer.frustration.builders.lazy

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
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

private lateinit var scope: CoroutineScope

@Composable
fun Lazy(onBackClick: () -> Unit) {
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
            text = "6. Create and run coroutine separately.",
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row {
            OutlinedButton(onClick = ::onCreate) {
                Text(text = "Create")
            }
            Spacer(modifier = Modifier.width(56.dp))
            OutlinedButton(onClick = ::onLaunch) {
                Text(text = "Run")
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Log()
    }
}

private lateinit var job: Job

@Suppress("BlockingMethodInNonBlockingContext")
private fun onCreate() {
    logMe {
        log("onCreate, start")

        job = scope.launch(start = CoroutineStart.LAZY) {
            log("coroutine, start")
            TimeUnit.MILLISECONDS.sleep(1000)
            log("coroutine, end")
        }

        log("onRun, end")
    }
}

private fun onLaunch() {
    logMe {
        log("onLaunch, start")
        job.start()
        log("onLaunch, end")
    }
}

