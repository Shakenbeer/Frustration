package com.shakenbeer.frustration.suspendfunc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shakenbeer.frustration.shared.ComposeLogger
import com.shakenbeer.frustration.shared.log
import kotlinx.coroutines.launch
import java.io.File
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class SuspendFuncViewModel: ViewModel() {

    private val logger = ComposeLogger()
    val lines = logger.lines
    private val networkService: NetworkService = NetworkServiceImpl(logger)

    private val currentThreadName: String
        get() = Thread.currentThread().name

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

    fun startLoadingFile() {
        with (logger) {
            start()
            log("start coroutine in $currentThreadName")
            viewModelScope.launch {
                log("inside coroutine call suspend function \"download\" in $currentThreadName")
                download("")
                log("inside coroutine after \"download\" executed in $currentThreadName")
            }
            log("execute code directly after coroutine started in $currentThreadName -> thread is not blocked")
        }
    }
}