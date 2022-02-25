package per.goweii.lube

import android.app.Activity
import android.content.Context
import android.view.View

val Context.activity: Activity?
    get() = ActivityUtils.findActivity(this)

val View.activity: Activity?
    get() = ActivityUtils.findActivity(this)