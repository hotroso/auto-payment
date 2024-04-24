package com.hotroso.autopay.listeners

import android.app.ActivityManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.ui.unit.Constraints
import com.hotroso.autopay.R
import com.hotroso.autopay.SettingActivity
import com.hotroso.autopay.common.Constants

class KeepAliveService : Service() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        Log.d("DEBUG", "KeepAliveService onCreate")
        super.onCreate()
        val notification = createNotification()
        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d("DEBUG", "KeepAliveService onStartCommand")
        startNotificationService()
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d("DEBUG", "KeepAliveService onBind")
        return null
    }

    override fun onUnbind(intent: Intent): Boolean {
        Log.d("DEBUG", "KeepAliveService onUnbind")
        tryReconnectService()
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("DEBUG", "KeepAliveService onDestroy")
        tryReconnectService()
    }

    override fun onTaskRemoved(rootIntent: Intent) {
        super.onTaskRemoved(rootIntent)
        Log.d("DEBUG", "KeepAliveService onTaskRemoved")
        tryReconnectService()
    }

    private fun tryReconnectService() {
        Log.d("DEBUG", "KeepAliveService tryReconnectService")
        //Send broadcast to restart service
        val broadcastIntent = Intent(applicationContext, NotificationServiceRestartReceiver::class.java)
        broadcastIntent.action = Constants.MY_ACTION_ON_REBOOT
        sendBroadcast(broadcastIntent)
    }

    private fun startNotificationService() {
        if (!isMyServiceRunning) {
            Log.d("DEBUG", "KeepAliveService startNotificationService")
            val mServiceIntent = Intent(this, NotifyListenerService::class.java)
            startService(mServiceIntent)
        }
    }

    private val isMyServiceRunning: Boolean
        get() {
            val manager: ActivityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
            for (service in manager.getRunningServices(Int.MAX_VALUE)) {
                if (NotifyListenerService::class.java.equals(service.service.className)) {
                    Log.i("isMyServiceRunning?", true.toString() + "")
                    return true
                }
            }
            Log.i("isMyServiceRunning?", false.toString() + "")
            return false
        }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotification(): Notification {
        val notificationChannelId = "sticky_notification_channel"

        // depending on the Android API that we're dealing with we will have
        // to use a specific method to create the notification
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            notificationChannelId,
            notificationChannelId,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            enableVibration(false)
            setShowBadge(false)
        }
        notificationManager.createNotificationChannel(channel)

        val pendingIntent: PendingIntent = Intent(this, SettingActivity::class.java).let {
                notificationIntent -> PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val builder: Notification.Builder = Notification.Builder(
            this,
            notificationChannelId
        )

        return builder
            .setContentTitle("Auto Pay")
            .setContentText("Auto Pay is in the background.")
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setSmallIcon(R.drawable.ic_notification)
            .build()
    }
}