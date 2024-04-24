package com.hotroso.autopay.listeners

import android.accessibilityservice.AccessibilityService
import android.app.Notification
import android.view.accessibility.AccessibilityEvent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompatExtras

class NotifyAccessibilityService: AccessibilityService() {

    private val REQUEST_CODE_ACCESS_NOTIFICATION = 100;
    private val REQUEST_CODE_SHOW_NOTIFICATION_ON_LOCK_SCREEN = 200;
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.eventType == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {

        }
    }


    override fun onInterrupt() {

    }

}
