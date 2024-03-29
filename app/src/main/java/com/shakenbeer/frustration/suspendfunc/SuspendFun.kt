package com.shakenbeer.frustration.suspendfunc

import androidx.compose.runtime.Composable
import com.shakenbeer.frustration.shared.Chapter
import com.shakenbeer.frustration.shared.logMe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.File
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

private val scope = CoroutineScope(Dispatchers.Main + Job())

private val networkService: NetworkService = NetworkServiceImpl()

@Composable
fun SuspendFunc(onBackClick: () -> Unit) {
    Chapter(
        "1. Our own suspend function \"download\".",
        onBackClick, ::startLoadingFile
    )
}

fun startLoadingFile() {
    logMe {
        clear()
        log("start coroutine")
        scope.launch {
            log("inside coroutine call suspend function \"download\"")
            download("")
            log("inside coroutine after \"download\" executed")
        }
        log("execute code directly after coroutine started -> thread is not blocked")
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