package per.goweii.lube.utils

object GCTrigger {
    @JvmStatic
    fun gc() {
        Runtime.getRuntime().gc()
    }
}