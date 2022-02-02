package com.shakenbeer.frustration.log

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ComposeLogger {
    var lines = mutableStateListOf<String>()
        private set

    fun start() = lines.clear()
    fun add(line: String) = lines.add(line)
}

fun ComposeLogger.log(msg: String) {
    add("${DateTimeFormatter.ISO_LOCAL_TIME.format(LocalTime.now())} $msg")
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