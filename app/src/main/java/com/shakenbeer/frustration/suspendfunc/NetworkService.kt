package com.shakenbeer.frustration.suspendfunc

import java.io.File
import java.lang.Exception

interface NetworkService {

    fun download(url: String, callback: Callback)

    interface Callback {
        fun onSuccess(result: File)
        fun onFailure(exception: Exception)
    }
}