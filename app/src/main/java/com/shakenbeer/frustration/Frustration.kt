package com.shakenbeer.frustration

import androidx.compose.runtime.Composable
import com.shakenbeer.frustration.intro.Intro
import com.shakenbeer.frustration.ui.theme.FrustrationTheme

@Composable
fun Frustration() {
    FrustrationTheme {
        Intro()
    }
}