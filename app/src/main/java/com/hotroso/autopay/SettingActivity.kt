package com.hotroso.autopay

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.hotroso.autopay.helpers.AutoStartHelper


class SettingActivity : AppCompatActivity() {

    private lateinit var notificationListenerUtil: NotificationListenerUtil
    private lateinit var notiLisPerLauncher: ActivityResultLauncher<Intent>

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