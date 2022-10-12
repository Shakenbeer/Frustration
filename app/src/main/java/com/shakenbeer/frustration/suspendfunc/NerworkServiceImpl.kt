package com.shakenbeer.frustration.suspendfunc

import com.shakenbeer.frustration.shared.logMe
import java.io.File
import java.util.concurrent.Executors

class NetworkServiceImpl : NetworkService {

    private val executor = Executors.newCachedThreadPool()

    override fun download(url: String, callback: NetworkService.Callback) {
        logMe {
            executor.execute {
                log("start loading file in ${Thread.currentThread().name}")
                Thread.sleep(1500)
                log("file loaded in ${Thread.currentThread().name}")
                callback.onSuccess(File("HelloWorld"))
            }
        }
    }
}