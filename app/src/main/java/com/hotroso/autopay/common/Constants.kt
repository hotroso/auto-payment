package com.hotroso.autopay.common

import com.hotroso.autopay.models.App

object Constants {
    val UNSUPPORTED_APPS: List<String> = mutableListOf(
            "android"
    )

    const val MY_ACTION_ON_REBOOT = "hotroso-auto-payment"
    const val PERMISSION_DIALOG_TITLE = "permission_dialog_title"
    const val PERMISSION_DIALOG_MSG = "permission_dialog_msg"
    const val PERMISSION_DIALOG_DENIED_TITLE = "permission_dialog_denied_title"
    const val PERMISSION_DIALOG_DENIED_MSG = "permission_dialog_denied_msg"
    const val PERMISSION_DIALOG_DENIED = "permission_dialog_denied"
    const val LOGS_DB_NAME = "logs_messages_db"
    const val NOTIFICATION_CHANNEL_ID = "autoreply"
    const val NOTIFICATION_CHANNEL_NAME = "autoreply_channel"

    val SUPPORTED_APPS: List<App> = mutableListOf(
        App("WhatsApp", "com.whatsapp"),
        App("Facebook Messenger", "com.facebook.orca")
    )

    const val MIN_DAYS = 0
    const val MAX_DAYS = 7

    // Notification Listener Service
    const val PERMISSION_DENIED = "Permission Denied"
    const val PERMISSION_GRANTED = "Permission Granted"
    const val RESTART_SERVICE_TOAST = "Service not enabled! Please restart the service from setting."



    /***
     * Xiaomi
     */
    const val BRAND_XIAOMI = "xiaomi"
    const val BRAND_XIAOMI_POCO = "poco"
    const val BRAND_XIAOMI_REDMI = "redmi"
    const val PACKAGE_XIAOMI_MAIN = "com.miui.securitycenter"
    const val PACKAGE_XIAOMI_COMPONENT = "com.miui.permcenter.autostart.AutoStartManagementActivity"

    /***
     * Letv
     */
    const val BRAND_LETV = "letv"
    const val PACKAGE_LETV_MAIN = "com.letv.android.letvsafe"
    const val PACKAGE_LETV_COMPONENT = "com.letv.android.letvsafe.AutobootManageActivity"

    /***
     * ASUS ROG
     */
    const val BRAND_ASUS = "asus"
    const val PACKAGE_ASUS_MAIN = "com.asus.mobilemanager"
    const val PACKAGE_ASUS_COMPONENT = "com.asus.mobilemanager.powersaver.PowerSaverSettings"
    const val PACKAGE_ASUS_COMPONENT_FALLBACK = "com.asus.mobilemanager.autostart.AutoStartActivity"

    /***
     * Honor
     */
    const val BRAND_HONOR = "honor"
    const val PACKAGE_HONOR_MAIN = "com.huawei.systemmanager"
    const val PACKAGE_HONOR_COMPONENT = "com.huawei.systemmanager.optimize.process.ProtectActivity"

    /***
     * Huawei
     */
    const val BRAND_HUAWEI = "huawei"
    const val PACKAGE_HUAWEI_MAIN = "com.huawei.systemmanager"
    const val PACKAGE_HUAWEI_COMPONENT = "com.huawei.systemmanager.optimize.process.ProtectActivity"
    const val PACKAGE_HUAWEI_COMPONENT_FALLBACK =
        "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity"

    /**
     * Oppo
     */
    const val BRAND_OPPO = "oppo"
    const val PACKAGE_OPPO_MAIN = "com.coloros.safecenter"
    const val PACKAGE_OPPO_FALLBACK = "com.oppo.safe"
    const val PACKAGE_OPPO_COMPONENT = "com.coloros.safecenter.permission.startup.StartupAppListActivity"
    const val PACKAGE_OPPO_COMPONENT_FALLBACK = "com.oppo.safe.permission.startup.StartupAppListActivity"
    const val PACKAGE_OPPO_COMPONENT_FALLBACK_A = "com.coloros.safecenter.startupapp.StartupAppListActivity"

    /**
     * Vivo
     */
    const val BRAND_VIVO = "vivo"
    const val PACKAGE_VIVO_MAIN = "com.iqoo.secure"
    const val PACKAGE_VIVO_FALLBACK = "com.vivo.perm;issionmanager"
    const val PACKAGE_VIVO_COMPONENT = "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity"
    const val PACKAGE_VIVO_COMPONENT_FALLBACK = "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"
    const val PACKAGE_VIVO_COMPONENT_FALLBACK_A = "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager"

    /**
     * Nokia
     */
    const val BRAND_NOKIA = "nokia"
    const val PACKAGE_NOKIA_MAIN = "com.evenwell.powersaving.g3"
    const val PACKAGE_NOKIA_COMPONENT = "com.evenwell.powersaving.g3.exception.PowerSaverExceptionActivity"

    /**
     * Samsung
     */
    const val BRAND_SAMSUNG = "samsung"
    const val PACKAGE_SAMSUNG_MAIN1 = "com.samsung.android.lool"
    const val PACKAGE_SAMSUNG_MAIN2 = "com.samsung.android.sm"
    const val PACKAGE_SAMSUNG_COMPONENT1 = "com.samsung.android.sm.ui.battery.BatteryActivity"
    const val PACKAGE_SAMSUNG_COMPONENT2 = "com.samsung.android.sm.battery.ui.BatteryActivity"

    /***
     * One plus
     */
    const val BRAND_ONE_PLUS = "oneplus"
    const val PACKAGE_ONE_PLUS_MAIN = "com.oneplus.security"
    const val PACKAGE_ONE_PLUS_COMPONENT = "com.oneplus.security.chainlaunch.view.ChainLaunchAppListActivity"

}