package com.shakenbeer.frustration.suspendfunc

import com.shakenbeer.frustration.shared.ComposeLogger
import java.io.File
import java.util.concurrent.Executors

class NetworkServiceImpl(private val logger: ComposeLogger) : NetworkService {

    private val executor = Executors.newCachedThreadPool()

    override fun download(url: String, callback: NetworkService.Callback) {
        executor.execute {
            logger.log("start loading file in ${Thread.currentThread().name}")
            Thread.sleep(1500)
            logger.log("file loaded in ${Thread.currentThread().name}")
            callback.onSuccess(File("HelloWorld"))
        }
    }
}