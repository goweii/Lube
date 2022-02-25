package per.goweii.lube

val Thread.isMainThread: Boolean
    get() = ThreadUtils.isMainThread