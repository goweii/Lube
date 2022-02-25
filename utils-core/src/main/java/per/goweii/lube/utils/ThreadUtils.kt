package per.goweii.lube.utils

import android.os.Looper

object ThreadUtils {
    @JvmStatic
    val isMainThread: Boolean
        get() = Thread.currentThread() === Looper.getMainLooper().thread
}