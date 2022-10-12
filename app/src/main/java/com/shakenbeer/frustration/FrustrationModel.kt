package com.shakenbeer.frustration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.shakenbeer.frustration.shared.clearLogs

class FrustrationModel : ViewModel() {
    var chapter by mutableStateOf(Chapter.CONTENT)
        private set

    fun onBack() {
        this.chapter = Chapter.CONTENT
    }

    fun onChapter(chapter: Chapter) {
        clearLogs()
        this.chapter = chapter
    }
}