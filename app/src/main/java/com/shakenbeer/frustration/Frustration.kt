package com.shakenbeer.frustration

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shakenbeer.frustration.intro.Intro
import com.shakenbeer.frustration.scope.Scope
import com.shakenbeer.frustration.suspendfunc.SuspendFunc
import com.shakenbeer.frustration.ui.theme.FrustrationTheme

@Composable
fun Frustration(frustrationModel: FrustrationModel = viewModel()) {
    FrustrationTheme {
        when (frustrationModel.chapter) {
            Chapter.CONTENT -> Content(frustrationModel)
            Chapter.INTRO -> Intro(frustrationModel::onBack)
            Chapter.SUSPEND_FUNC -> SuspendFunc(frustrationModel::onBack)
            Chapter.SCOPE -> Scope(frustrationModel::onBack)
        }
    }
}

@Composable
fun Content(frustrationModel: FrustrationModel) {
    LazyColumn(modifier = Modifier
        .padding(24.dp)
        .fillMaxWidth()) {
        Chapter.values().slice(1 until Chapter.values().size).forEach { chapter ->
            item {
                Box(modifier = Modifier
                    .clickable { frustrationModel.onChapter(chapter) }
                    .height(44.dp)
                    .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterStart),
                        text = chapter.display,
                        style = MaterialTheme.typography.body1)
                }

            }
        }
    }
}