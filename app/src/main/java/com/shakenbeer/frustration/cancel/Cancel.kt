package com.shakenbeer.frustration.cancel

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
fun Cancel(onBackClick: () -> Unit) {
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
            text = "4. Run and cancel a coroutine, play around.",
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row {
            OutlinedButton(onClick = ::onRun) {
                Text(text = "Run")
            }
            Spacer(modifier = Modifier.width(56.dp))
            OutlinedButton(onClick = ::onCancel) {
                Text(text = "Cancel")
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Log()
    }
}

lateinit var job: Job

@Suppress("BlockingMethodInNonBlockingContext")
private fun onRun() {
    logMe {
        clear()
        log("onRun, start")

        job = scope.launch {
            log("coroutine, start")
            var x = 0
            while (x < 5 && isActive) {
                TimeUnit.MILLISECONDS.sleep(1000)
                log("coroutine, ${x++}")
            }
            log("coroutine, end")
        }

        log("onRun, end")
    }

}

private fun onCancel() {
    logMe {
        if (::job.isInitialized) {
            log("onCancel -> only changes a status of coroutine")
            job.cancel()
        } else {
            log("onCancel -> coroutine didn't start")
        }
    }
}


