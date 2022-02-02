package com.shakenbeer.frustration.intro

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shakenbeer.frustration.Start

@Composable
fun Intro(introViewModel: IntroViewModel = viewModel()) {
    Start(
        "1. Coroutine could be started in the main thread.",
        introViewModel.lines, introViewModel::startCoroutineInTheMainThread
    )
}