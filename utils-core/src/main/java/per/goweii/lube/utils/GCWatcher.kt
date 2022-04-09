package per.goweii.lube.utils

import java.lang.ref.WeakReference

object GCWatcher {
    private var reference: WeakReference<Owner>? = null
    private var listeners: ArrayList<() -> Boolean>? = null

    /**
     * 监听GC执行
     * 如果返回false那么这个监听器在触发之后会移除，只触发一次
     * 如果返回true则不会移除，直到某一次触发时为false为止
     */
    @JvmStatic
    fun watch(listener: () -> Boolean) {
        if (listeners == null) {
            listeners = arrayListOf()
        }
        listeners!!.add(listener)
        if (reference?.get() == null) {
            reference = WeakReference(Owner())
        }
    }

    private class Owner {
        protected fun finalize() {
            listeners?.iterator()?.let { iterator ->
                while (iterator.hasNext()) {
                    val next = iterator.next()
                    if (!next.invoke()) {
                        iterator.remove()
                    }
                }
            }
            if (!listeners.isNullOrEmpty()) {
                reference = WeakReference(Owner())
            }
        }
    }
}