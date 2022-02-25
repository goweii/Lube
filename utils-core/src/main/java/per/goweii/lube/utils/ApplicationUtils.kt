package per.goweii.lube.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.graphics.drawable.Drawable
import android.os.Build

object ApplicationUtils {
    /**
     * 获取指定包名对应应用的应用名
     *
     * @param context Context
     * @param packageName 应用的包名，默认为当前应用
     * @return 应用名，如果包名不存在则返回空字符
     */
    @JvmStatic
    @JvmOverloads
    fun getLabel(context: Context, packageName: String = context.packageName): String {
        if (packageName.isBlank()) return ""
        val packageInfo = getPackageInfo(context, packageName) ?: return ""
        return packageInfo.applicationInfo.loadLabel(context.packageManager).toString()
    }

    /**
     * 获取指定包名对应应用的Icon
     *
     * @param context Context
     * @param packageName 应用的包名，默认为当前应用
     * @return 应用Icon，如果包名不存在则返回空
     */
    @JvmStatic
    @JvmOverloads
    fun getIcon(context: Context, packageName: String = context.packageName): Drawable? {
        if (packageName.isBlank()) return null
        val packageInfo = getPackageInfo(context, packageName) ?: return null
        return packageInfo.applicationInfo.loadIcon(context.packageManager)
    }

    /**
     * 获取指定包名对应应用的版本名
     *
     * @param context Context
     * @param packageName 应用的包名，默认为当前应用
     * @return 应用版本名，如1.0.0，如果包名不存在则返回空字符
     */
    @JvmStatic
    @JvmOverloads
    fun getVersionName(context: Context, packageName: String = context.packageName): String {
        if (packageName.isBlank()) return ""
        val packageInfo = getPackageInfo(context, packageName) ?: return ""
        return packageInfo.versionName
    }

    /**
     * 获取指定包名对应应用的版本号
     *
     * @param context Context
     * @param packageName 应用的包名，默认为当前应用
     * @return 应用版本号，如1，如果包名不存在则返回0
     */
    @Suppress("DEPRECATION")
    @JvmStatic
    @JvmOverloads
    fun getVersionCode(context: Context, packageName: String = context.packageName): Long {
        if (packageName.isBlank()) return 0L
        val packageInfo = getPackageInfo(context, packageName) ?: return 0L
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            packageInfo.longVersionCode
        } else {
            packageInfo.versionCode.toLong()
        }
    }

    /**
     * 获取指定包名对应应用的签名
     *
     * @param context Context
     * @param packageName 应用的包名，默认为当前应用
     * @return 应用签名，如果包名不存在则返回null
     */
    @Suppress("DEPRECATION")
    @JvmStatic
    @JvmOverloads
    fun getSignatures(
        context: Context,
        packageName: String = context.packageName
    ): Array<Signature>? {
        if (packageName.isBlank()) return null
        val packageInfo = getPackageInfo(context, packageName) ?: return null
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            packageInfo.signingInfo?.apkContentsSigners
        } else {
            packageInfo.signatures
        }
    }

    /**
     * 获取指定包名对应应用的PackageInfo
     *
     * @param context Context
     * @param packageName 应用的包名，默认为当前应用
     * @return PackageInfo，如果包名不存在则返回null
     */
    @JvmStatic
    @JvmOverloads
    fun getPackageInfo(context: Context, packageName: String = context.packageName): PackageInfo? {
        if (packageName.isBlank()) return null
        return try {
            context.packageManager.getPackageInfo(packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            null
        }
    }
}