package com.hotroso.autopay.utils

import android.service.notification.StatusBarNotification
import android.os.Parcelable
import java.util.*
import java.util.stream.Stream

object NotificationUtils {
    private const val MAX_OLD_NOTIFICATION_CAN_BE_REPLIED_TIME_MS = 2 * 60 * 1000
    fun getTitle(sbn: StatusBarNotification): String? {
        var title: String?
        if (sbn.notification.extras.getBoolean("android.isGroupConversation")) {
            title = sbn.notification.extras.getString("android.hiddenConversationTitle")

            if (title == null) {
                title = sbn.notification.extras.getString("android.title")
                val index = title!!.indexOf(':')
                if (index != -1) {
                    title = title.substring(0, index)
                }
            }

            val b = sbn.notification.extras["android.messages"] as Array<Parcelable>?
            if (b != null && b.size > 1) {
                val startIndex = title.lastIndexOf('(')
                if (startIndex != -1) {
                    title = title.substring(0, startIndex)
                }
            }
        } else {
            title = sbn.notification.extras.getString("android.title")
        }
        return title
    }

    fun isNewNotification(sbn: StatusBarNotification): Boolean {

        return sbn.notification.`when` == 0L ||
                System.currentTimeMillis() - sbn.notification.`when` < MAX_OLD_NOTIFICATION_CAN_BE_REPLIED_TIME_MS
    }

    fun getTitleRaw(sbn: StatusBarNotification): String? {
        val extras = sbn.notification.extras
        if (extras.getCharSequence("android.text") != null) {
            return extras.getCharSequence("android.title").toString()
        }
        return sbn.notification.extras.getString("android.title")
    }

    private fun validateMsg(msg: String?): String {
        if (isValidMessage(msg)) {
            return msg.toString()
        }
        return ""
    }

    fun getMessage(sbn: StatusBarNotification): String? {
        val extras = sbn.notification.extras
        if (extras.getCharSequence("android.text") != null) {
            return validateMsg(extras.getCharSequence("android.text").toString())
        }
        if (extras.getString("android.text") != null) {
            return validateMsg(extras.getString("android.text"))
        }
        return "";
    }

    private fun isValidMessage(msg: String?): Boolean {
        if ((msg != null) && (msg.length > 2)) {
            val data = arrayOf("This message was deleted", "new messages", "\uD83D\uDCF7 Photo",
                "Calling…", "Ringing…", "Missed voice call", "Incoming voice call", "Ongoing video call",
                "Sticker", "missed calls", "\uD83D\uDCF9 Incoming video call", "GIF")
            return Stream.of(data).allMatch { str -> !msg.contains(str.toString()) };
        }
        return true;
    }
}