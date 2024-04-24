package com.hotroso.autopay

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import com.hotroso.autopay.common.Constants
import com.hotroso.autopay.helpers.AutoStartHelper
import com.hotroso.autopay.utils.NotificationListenerUtil


class SettingActivity : AppCompatActivity() {

    private lateinit var notificationListenerUtil: NotificationListenerUtil
    private lateinit var notiLisPerLauncher: ActivityResultLauncher<Intent>
    private val REQUEST_CODE_NOTIFICATION_POLICY_ACCESS = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        notificationListenerUtil = NotificationListenerUtil(this)


        notiLisPerLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                val granted = notificationListenerUtil.isNotificationServiceEnabled()
                handleNotificationListenerPermissionResult(granted)
            }
    }

    private fun handleNotificationListenerPermissionResult(granted: Boolean) {
        Toast.makeText(this, Constants.PERMISSION_GRANTED, Toast.LENGTH_SHORT).show()
    }

    fun settingPermission(view: View?) {
        notificationListenerUtil.requestNotificationListenerPermission(notiLisPerLauncher)
    }

    fun checkAutoStartPermission(view: View) {
        AutoStartHelper.instance.getAutoStartPermission(applicationContext)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun notifyOnLockScreenPermission(v: View) {
        var pers = arrayOf(Manifest.permission.POST_NOTIFICATIONS,
            Manifest.permission.ACCESS_NOTIFICATION_POLICY,
            Manifest.permission.BIND_NOTIFICATION_LISTENER_SERVICE)
        ActivityCompat.requestPermissions(this, pers, REQUEST_CODE_NOTIFICATION_POLICY_ACCESS)
    }

    @SuppressLint("BatteryLife")
    fun permissionBattery(view: View) {
        val powerManager = applicationContext.getSystemService(Context.POWER_SERVICE) as PowerManager
        if (!powerManager.isIgnoringBatteryOptimizations(packageName)) {
            startActivity(
                Intent(
                    Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
                    Uri.parse("package:$packageName")
                )
            )
        } else {
            Toast.makeText(this, "Em setting xong roi", Toast.LENGTH_SHORT).show();
        }
    }

}