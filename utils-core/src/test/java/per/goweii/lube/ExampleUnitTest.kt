package per.goweii.lube

import org.junit.Test

import org.junit.Assert.*
import per.goweii.lube.utils.GCTrigger
import per.goweii.lube.utils.GCWatcher

class ExampleUnitTest {
    @Test
    fun gc_test() {
        GCWatcher.watch {
            println("gc ...")
            true
        }

        repeat(10) {
            println("gc -->")
            GCTrigger.gc()
            println("gc <--")
            Thread.sleep(100)
        }
    }
}