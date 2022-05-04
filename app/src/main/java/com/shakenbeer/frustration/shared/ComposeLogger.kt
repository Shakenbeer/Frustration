package com.shakenbeer.frustration.shared

import androidx.compose.runtime.mutableStateListOf
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ComposeLogger {
    var lines = mutableStateListOf<String>()
        private set

    fun start() = lines.clear()

    fun log(msg: String) {
        lines.add("${DateTimeFormatter.ISO_LOCAL_TIME.format(LocalTime.now())} $msg")
    }
}


