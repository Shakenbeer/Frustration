package com.shakenbeer.frustration.suspendfunc

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shakenbeer.frustration.shared.ComposeLogger
import com.shakenbeer.frustration.shared.Start
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.File
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

private val logger = ComposeLogger()
private val scope = CoroutineScope(Dispatchers.Main + Job())
private val currentThreadName: String
    get() = Thread.currentThread().name

private val networkService: NetworkService = NetworkServiceImpl(logger)

@Composable
fun SuspendFunc() {
    Start(
        "1. Our own suspend function \"download\".",
        logger.lines, ::startLoadingFile
    )
}

fun startLoadingFile() {
    with(logger) {
        start()
        log("start coroutine in $currentThreadName")
        scope.launch {
            log("inside coroutine call suspend function \"download\" in $currentThreadName")
            download("")
            log("inside coroutine after \"download\" executed in $currentThreadName")
        }
        log("execute code directly after coroutine started in $currentThreadName -> thread is not blocked")
    }
}

suspend fun download(url: String): File {
    return suspendCoroutine { continuation ->
        networkService.download(url, object : NetworkService.Callback {
            override fun onSuccess(result: File) {
                continuation.resume(result)
            }

            override fun onFailure(exception: Exception) {
                continuation.resumeWithException(exception)
            }
        })
    }
}