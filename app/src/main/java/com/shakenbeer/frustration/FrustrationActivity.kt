package com.shakenbeer.frustration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shakenbeer.frustration.databinding.ActivityFrustrationBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class FrustrationActivity : AppCompatActivity() {

    private val scope = CoroutineScope(Job())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frustration)
        val binding = ActivityFrustrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener { startCoroutineInTheMainThread(binding) }

    }

    private fun startCoroutineInTheMainThread(binding: ActivityFrustrationBinding) {
        binding.logTextView.text = ""
        log("We start coroutine in \"${Thread.currentThread().name}\"", binding)
        scope.launch {
            log(
                "We are inside coroutine and start suspend function \"delay(1 second)\" in \"${Thread.currentThread().name}\"",
                binding
            )
            delay(1000L)
            log(
                "We are inside coroutine after \"delay\" executed, and current thread is still \"${Thread.currentThread().name}\"",
                binding
            )
        }
        log(
            "We execute some code directly after coroutine is started in \"${Thread.currentThread().name}\" -> thread is not blocked",
            binding
        )
    }

    private fun log(msg: String, binding: ActivityFrustrationBinding) {
        with(binding.logTextView) {
            post {
                if (text.isNotEmpty()) text = "$text\n"
                text = "$text${DateTimeFormatter.ISO_LOCAL_TIME.format(LocalTime.now())} $msg"
            }
        }
    }

    companion object {
        private const val TAG = "FrustrationTag"
    }
}