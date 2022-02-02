package com.shakenbeer.frustration

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shakenbeer.frustration.intro.Intro
import com.shakenbeer.frustration.ui.theme.FrustrationTheme

@Composable
fun Frustration(frustrationModel: FrustrationModel = viewModel()) {
    FrustrationTheme {
        when(frustrationModel.chapter) {
            Chapter.INTRO -> Intro()
        }
    }
}