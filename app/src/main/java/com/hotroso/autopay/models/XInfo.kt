package com.hotroso.autopay.models

import android.service.notification.StatusBarNotification
import com.hotroso.autopay.utils.NotificationUtils
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class XInfo(sbn: StatusBarNotification) {

    private var groupKey: String?
    private var postTime: Date? = null;
    private var strPostTime: String? = null;
    private var title: String? = null;
    private var msg: String? = null;
    private var pck: String? = null;

    fun getTitle(): String? {
        return title
    }

    fun getPck(): String? {
        return pck
    }

    init {
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        postTime = Date(sbn.postTime)
        strPostTime = postTime?.let { df.format(it) }
        title = NotificationUtils.getTitleRaw(sbn)
        msg = NotificationUtils.getMessage(sbn)
        groupKey = sbn.groupKey
        pck = sbn.packageName

    }


}