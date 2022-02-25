package per.goweii.lube

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import java.io.BufferedReader
import java.io.FileReader

object ProcessUtils {
    @JvmStatic
    val myPid: Int by lazy {
        android.os.Process.myPid()
    }

    @JvmStatic
    val currentProcessName: String? by lazy {
        var processName = getCurrentProcessNameOnP()
        if (processName.isNullOrBlank()) {
            processName = getCurrentProcessNameByRe()
        }
        if (processName.isNullOrBlank()) {
            processName = this.getProcessName(myPid)
        }
        processName
    }

    @JvmStatic
    fun getProcessName(pid: Int): String? {
        return try {
            BufferedReader(FileReader("/proc/$pid/cmdline"))
                .use { reader ->
                    val line = reader.readLine()
                    if (!line.isNullOrBlank()) {
                        return@use line.trim { it <= ' ' }
                    }
                    return@use null
                }
        } catch (e: Throwable) {
            null
        }
    }

    private fun getCurrentProcessNameOnP(): String? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            return Application.getProcessName()
        }
        return null
    }

    @SuppressLint("DiscouragedPrivateApi", "PrivateApi")
    private fun getCurrentProcessNameByRe(): String? {
        try {
            val clazz = Class.forName(
                "android.app.ActivityThread",
                false, Application::class.java.classLoader
            )
            val method = clazz.getDeclaredMethod("currentProcessName")
            method.isAccessible = true
            val obj = method.invoke(null)
            if (obj is String) {
                return obj
            }
        } catch (e: Throwable) {
        }
        return null
    }
}