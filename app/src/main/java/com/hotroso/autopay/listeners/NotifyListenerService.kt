package com.hotroso.autopay.listeners

import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.google.gson.Gson
import com.hotroso.autopay.common.Constants
import com.hotroso.autopay.workers.PushingWorker
import com.hotroso.autopay.models.XInfo

class NotifyListenerService : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        super.onNotificationPosted(sbn)

        if (Constants.UNSUPPORTED_APPS.contains(sbn.packageName)) {
            return
        }

        startWorker(XInfo(sbn))
    }

    private fun startWorker(info: XInfo) {
        val data = Data.Builder()
            .putString("xx_data", Gson().toJson(info))
            .build()

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)

        val oneTimeRequest = OneTimeWorkRequest.Builder(PushingWorker::class.java)
            .setInputData(data)
            .setConstraints(constraints.build())
            .addTag("demo")
            .build()

        //Toast.makeText(this, "Starting worker", Toast.LENGTH_SHORT).show()

        WorkManager.getInstance(this)
            .enqueueUniqueWork("auto-payment", ExistingWorkPolicy.KEEP, oneTimeRequest)
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("XXX", "EMMMMM onCreate")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Log.d("XXX", "EMMMMM onStartCommand")
        return START_STICKY
    }

}
