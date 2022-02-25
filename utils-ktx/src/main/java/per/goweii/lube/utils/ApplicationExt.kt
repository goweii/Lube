package per.goweii.lube.utils

import android.content.Context
import android.graphics.drawable.Drawable

fun Context.getAppLabel(packageName: String = getPackageName()): String {
    return ApplicationUtils.getLabel(this, packageName)
}

fun Context.getAppIcon(packageName: String = getPackageName()): Drawable? {
    return ApplicationUtils.getIcon(this, packageName)
}

fun Context.getVersionName(packageName: String = getPackageName()): String {
    return ApplicationUtils.getVersionName(this, packageName)
}

fun Context.getVersionCode(packageName: String = getPackageName()): Long {
    return ApplicationUtils.getVersionCode(this, packageName)
}