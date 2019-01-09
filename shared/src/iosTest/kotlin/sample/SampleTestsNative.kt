package sample

import kotlin.native.concurrent.freeze
import kotlin.test.Test
import kotlin.test.assertTrue

class SampleTestsNative {
    @Test
    fun testHello() {
        val head: A = A()
        val mid = A(id = 1)
        head.child = mid
        val tail = A(id = 2)
        mid.child = tail
        tail.child = head
        head.freeze()
    }
}

data class A(val id: Int = 0, var child: A? = null)