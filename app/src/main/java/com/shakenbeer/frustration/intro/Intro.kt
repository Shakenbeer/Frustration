package com.shakenbeer.frustration.intro

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shakenbeer.frustration.log.Log
import com.shakenbeer.frustration.ui.theme.FrustrationTheme

@Composable
fun Intro(introViewModel: IntroViewModel = viewModel()) {
    Start(introViewModel.lines, introViewModel::startCoroutineInTheMainThread)
}

@Composable
fun Start(lines: List<String>, onStartClick: () -> Unit) {
    Column(
        modifier = Modifier.padding(24.dp)
    ) {
        Text(
            text = "1. Coroutine could be started in the main thread.",
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedButton(onClick = onStartClick) {
            Text(text = "Start")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Log(lines = lines)
    }
}

@Preview
@Composable
fun StartPreview() {
    FrustrationTheme {
        Start(listOf("Log Line 1", "Log Line 2", "Log Line 3")) { }
    }
}