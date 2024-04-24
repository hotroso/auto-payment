package com.hotroso.autopay.helpers

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.pm.ApplicationInfo
import android.os.Build
import android.widget.Toast
import com.hotroso.autopay.common.Constants
import com.hotroso.autopay.common.Constants.PACKAGE_ASUS_COMPONENT
import com.hotroso.autopay.common.Constants.PACKAGE_ASUS_COMPONENT_FALLBACK
import com.hotroso.autopay.common.Constants.PACKAGE_ASUS_MAIN
import com.hotroso.autopay.common.Constants.PACKAGE_HONOR_COMPONENT
import com.hotroso.autopay.common.Constants.PACKAGE_HONOR_MAIN
import com.hotroso.autopay.common.Constants.PACKAGE_HUAWEI_COMPONENT
import com.hotroso.autopay.common.Constants.PACKAGE_HUAWEI_COMPONENT_FALLBACK
import com.hotroso.autopay.common.Constants.PACKAGE_HUAWEI_MAIN
import com.hotroso.autopay.common.Constants.PACKAGE_LETV_COMPONENT
import com.hotroso.autopay.common.Constants.PACKAGE_LETV_MAIN
import com.hotroso.autopay.common.Constants.PACKAGE_NOKIA_COMPONENT
import com.hotroso.autopay.common.Constants.PACKAGE_NOKIA_MAIN
import com.hotroso.autopay.common.Constants.PACKAGE_ONE_PLUS_COMPONENT
import com.hotroso.autopay.common.Constants.PACKAGE_ONE_PLUS_MAIN
import com.hotroso.autopay.common.Constants.PACKAGE_OPPO_COMPONENT
import com.hotroso.autopay.common.Constants.PACKAGE_OPPO_COMPONENT_FALLBACK
import com.hotroso.autopay.common.Constants.PACKAGE_OPPO_COMPONENT_FALLBACK_A
import com.hotroso.autopay.common.Constants.PACKAGE_OPPO_FALLBACK
import com.hotroso.autopay.common.Constants.PACKAGE_OPPO_MAIN
import com.hotroso.autopay.common.Constants.PACKAGE_VIVO_COMPONENT
import com.hotroso.autopay.common.Constants.PACKAGE_VIVO_COMPONENT_FALLBACK
import com.hotroso.autopay.common.Constants.PACKAGE_VIVO_COMPONENT_FALLBACK_A
import com.hotroso.autopay.common.Constants.PACKAGE_VIVO_FALLBACK
import com.hotroso.autopay.common.Constants.PACKAGE_VIVO_MAIN
import com.hotroso.autopay.common.Constants.PACKAGE_XIAOMI_COMPONENT
import com.hotroso.autopay.common.Constants.PACKAGE_XIAOMI_MAIN
import com.hotroso.autopay.R
import java.util.*

//Ref: https://stackoverflow.com/q/44383983
class AutoStartHelper private constructor() {

    fun getAutoStartPermission(context: Context) {
        when (Build.BRAND.lowercase(Locale.getDefault())) {
            Constants.BRAND_ASUS -> autoStartAsus(context)
            Constants.BRAND_XIAOMI, Constants.BRAND_XIAOMI_POCO, Constants.BRAND_XIAOMI_REDMI -> autoStartXiaomi(
                context
            )

            Constants.BRAND_LETV -> autoStartLetv(context)
            Constants.BRAND_HONOR -> autoStartHonor(context)
            Constants.BRAND_HUAWEI -> autoStartHuawei(context)
            Constants.BRAND_OPPO -> autoStartOppo(context)
            Constants.BRAND_VIVO -> autoStartVivo(context)
            Constants.BRAND_NOKIA -> autoStartNokia(context)
            Constants.BRAND_SAMSUNG -> autoStartSamsung(context)
            Constants.BRAND_ONE_PLUS -> autoStartOnePlus(context)
            else -> Toast.makeText(
                context, context.getString(R.string.setting_not_available_for_device),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun autoStartSamsung(context: Context) {
        val packageName: String
        packageName =
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) Constants.PACKAGE_SAMSUNG_MAIN1 else Constants.PACKAGE_SAMSUNG_MAIN2
        if (isPackageExists(context, packageName)) {
            try {
                startIntent(context, packageName, Constants.PACKAGE_SAMSUNG_COMPONENT1)
            } catch (e: Exception) {
                e.printStackTrace()
                try {
                    startIntent(context, packageName, Constants.PACKAGE_SAMSUNG_COMPONENT2)
                } catch (e1: Exception) {
                    e1.printStackTrace()
                }
            }
        }
    }

    private fun autoStartAsus(context: Context) {
        if (isPackageExists(context, PACKAGE_ASUS_MAIN)) {
            try {
                startIntent(
                    context,
                    PACKAGE_ASUS_MAIN,
                    PACKAGE_ASUS_COMPONENT
                )
            } catch (e: Exception) {
                e.printStackTrace()
                try {
                    startIntent(context, PACKAGE_ASUS_MAIN, PACKAGE_ASUS_COMPONENT_FALLBACK)
                } catch (e1: Exception) {
                    e1.printStackTrace()
                }
            }
        }
    }

    private fun autoStartXiaomi(context: Context) {
        if (isPackageExists(context, PACKAGE_XIAOMI_MAIN)) {
            try {
                startIntent(context, PACKAGE_XIAOMI_MAIN, PACKAGE_XIAOMI_COMPONENT)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun autoStartLetv(context: Context) {
        if (isPackageExists(context, PACKAGE_LETV_MAIN)) {
            try {
                startIntent(context, PACKAGE_LETV_MAIN, PACKAGE_LETV_COMPONENT)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun autoStartHonor(context: Context) {
        if (isPackageExists(context, PACKAGE_HONOR_MAIN)) {
            try {
                startIntent(context, PACKAGE_HONOR_MAIN, PACKAGE_HONOR_COMPONENT)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun autoStartHuawei(context: Context) {
        if (isPackageExists(context, PACKAGE_HUAWEI_MAIN)) {
            try {
                startIntent(context, PACKAGE_HUAWEI_MAIN, PACKAGE_HUAWEI_COMPONENT)
            } catch (e: Exception) {
                e.printStackTrace()
                try {
                    startIntent(context, PACKAGE_HUAWEI_MAIN, PACKAGE_HUAWEI_COMPONENT_FALLBACK)
                } catch (e1: Exception) {
                    e1.printStackTrace()
                }
            }
        }
    }

    private fun autoStartOppo(context: Context) {
        if (isPackageExists(context, PACKAGE_OPPO_MAIN) || isPackageExists(
                context,
                PACKAGE_OPPO_FALLBACK
            )
        ) {
            try {
                startIntent(context, PACKAGE_OPPO_MAIN, PACKAGE_OPPO_COMPONENT)
            } catch (e: Exception) {
                e.printStackTrace()
                try {
                    startIntent(context, PACKAGE_OPPO_FALLBACK, PACKAGE_OPPO_COMPONENT_FALLBACK)
                } catch (ex: Exception) {
                    ex.printStackTrace()
                    try {
                        startIntent(
                            context,
                            PACKAGE_OPPO_MAIN,
                            PACKAGE_OPPO_COMPONENT_FALLBACK_A
                        )
                    } catch (exx: Exception) {
                        exx.printStackTrace()
                    }
                }
            }
        }
    }

    private fun autoStartVivo(context: Context) {
        if (isPackageExists(context, PACKAGE_VIVO_MAIN) || isPackageExists(
                context,
                PACKAGE_VIVO_FALLBACK
            )
        ) {
            try {
                startIntent(context, PACKAGE_VIVO_MAIN, PACKAGE_VIVO_COMPONENT)
            } catch (e: Exception) {
                e.printStackTrace()
                try {
                    startIntent(context, PACKAGE_VIVO_FALLBACK, PACKAGE_VIVO_COMPONENT_FALLBACK)
                } catch (ex: Exception) {
                    ex.printStackTrace()
                    try {
                        startIntent(
                            context,
                            PACKAGE_VIVO_MAIN,
                            PACKAGE_VIVO_COMPONENT_FALLBACK_A
                        )
                    } catch (exx: Exception) {
                        exx.printStackTrace()
                    }
                }
            }
        }
    }

    private fun autoStartNokia(context: Context) {
        if (isPackageExists(context, PACKAGE_NOKIA_MAIN)) {
            try {
                startIntent(context, PACKAGE_NOKIA_MAIN, PACKAGE_NOKIA_COMPONENT)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun autoStartOnePlus(context: Context) {
        if (isPackageExists(context, PACKAGE_ONE_PLUS_MAIN)) {
            try {
                startIntent(context, PACKAGE_ONE_PLUS_MAIN, PACKAGE_ONE_PLUS_COMPONENT)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    @Throws(Exception::class)
    private fun startIntent(context: Context, packageName: String, componentName: String) {
        try {
            val intent = Intent()
            intent.component = ComponentName(packageName, componentName)
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (var5: Exception) {
            var5.printStackTrace()
            throw var5
        }
    }

    private fun isPackageExists(context: Context, targetPackage: String): Boolean {
        val packages: List<ApplicationInfo>
        val pm = context.packageManager
        packages = pm.getInstalledApplications(0)
        for (packageInfo in packages) {
            if (packageInfo.packageName == targetPackage) {
                return true
            }
        }
        return false
    }

    companion object {
        @JvmStatic
        val instance: AutoStartHelper
            get() = AutoStartHelper()
    }
}