package per.goweii.lube.utils

import android.app.Activity
import android.content.Context
import android.view.View

val Context.activity: Activity?
    get() = ActivityUtils.findActivity(this)

val View.activity: Activity?
    get() = ActivityUtils.findActivity(this)