package eu.fundacjabt.lokator

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class RunningService : Service() {

    private var timerJob: Job? = null

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            Actions.START.toString() -> startTimer()
            Actions.STOP.toString() -> stopTimer()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startTimer() {
        val notification = NotificationCompat.Builder(this, "running_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Running Notification")
            .setContentText("Placeholder Text")
            .build()



        timerJob = GlobalScope.launch {
            repeat(Int.MAX_VALUE) {
                printTestLog()
                startForeground(1, notification)
                delay(5000) // Delay for 2 seconds
            }
        }
    }

    private fun stopTimer() {
        timerJob?.cancel()
        stopForeground(true)
        stopSelf()
    }

    private fun printTestLog() {
        println("Test log at ${System.currentTimeMillis()}")
    }

    enum class Actions {
        START, STOP
    }
}
