package com.shakenbeer.frustration.suspendfunc

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shakenbeer.frustration.Start

@Composable
fun SuspendFunc(viewModel: SuspendFuncViewModel = viewModel()) {
    Start(
        "1. Our own suspend function \"download\".",
        viewModel.lines, viewModel::startLoadingFile
    )
}