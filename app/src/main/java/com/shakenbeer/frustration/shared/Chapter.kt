package com.shakenbeer.frustration.shared

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shakenbeer.frustration.ui.theme.FrustrationTheme

@Composable
fun Chapter(title: String, lines: List<String>, onBackClick: () -> Unit, onStartClick: () -> Unit) {
    Column(
        modifier = Modifier.padding(24.dp)
    ) {
        TextButton(onClick = onBackClick) {
            Text(text = "Back")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = title,
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

@Preview
@Composable
fun StartPreview() {
    FrustrationTheme {
        Chapter("Chapter Title", listOf("Log Line 1", "Log Line 2", "Log Line 3"), { }) { }
    }
}