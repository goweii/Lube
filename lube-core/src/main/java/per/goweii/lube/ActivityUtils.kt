package per.goweii.lube

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View
import android.view.ViewGroup

object ActivityUtils {
    @JvmStatic
    fun findActivity(context: Context): Activity? {
        var ctx = context
        while (true) {
            if (ctx is Activity) {
                return ctx
            }
            if (ctx is ContextWrapper) {
                val baseContext = ctx.baseContext
                if (baseContext !== ctx) {
                    ctx = baseContext
                    continue
                }
            }
            return null
        }
    }

    @JvmStatic
    fun findActivity(view: View): Activity? {
        findActivity(view.context)?.let { return it }
        val rootView = view.rootView
        if (rootView != null && rootView !== view) {
            findActivity(rootView.context)?.let { return it }
        }
        var parent = view.parent
        while (parent != null && parent is ViewGroup && parent !== rootView) {
            findActivity(parent.context)?.let { return it }
            parent = parent.parent
        }
        return null
    }
}