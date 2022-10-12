package com.shakenbeer.frustration.shared

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ComposeLogger {
    val lines = mutableStateListOf<String>()

    private val currentThreadName: String
        get() = Thread.currentThread().name

    fun clear() = lines.clear()

    fun log(msg: String) {
        lines.add("${DateTimeFormatter.ISO_LOCAL_TIME.format(LocalTime.now())} $msg [$currentThreadName]")
    }
}

private val logger = ComposeLogger()

internal fun clearLogs() = logger.clear()

@Composable
fun Log() {
    Log(logger.lines)
}

@Composable
fun Log(lines: List<String>) {
    LazyColumn {
        lines.forEach { line ->
            item {
                Log(line = line)
            }
        }
    }
}

@Composable
fun Log(line: String) {
    Text(
        text = line,
        style = MaterialTheme.typography.body2
    )
}

fun logMe(block: ComposeLogger.() -> Unit) {
    with (logger) {
        block()
    }
}


