package com.shakenbeer.frustration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class FrustrationModel : ViewModel() {
    val chapter by mutableStateOf(Chapter.INTRO)
}