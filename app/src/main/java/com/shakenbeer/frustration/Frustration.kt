package com.shakenbeer.frustration

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shakenbeer.frustration.intro.Intro
import com.shakenbeer.frustration.scope.Scope
import com.shakenbeer.frustration.suspendfunc.SuspendFunc
import com.shakenbeer.frustration.ui.theme.FrustrationTheme

@Composable
fun Frustration(frustrationViewModel: FrustrationViewModel = viewModel()) {
    FrustrationTheme {
        when (frustrationViewModel.chapter) {
            Chapter.INTRO -> Intro()
            Chapter.SUSPEND_FUNC -> SuspendFunc()
            Chapter.SCOPE -> Scope()
        }
    }
}