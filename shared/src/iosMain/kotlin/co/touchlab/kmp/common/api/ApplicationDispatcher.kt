package co.touchlab.kmp.common.api

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import platform.darwin.dispatch_queue_t
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.DetachedObjectGraph
import kotlin.native.concurrent.TransferMode
import kotlin.native.concurrent.freeze
import kotlin.native.concurrent.isFrozen
import kotlin.test.assertTrue

internal actual val ApplicationDispatcher: CoroutineDispatcher = NsQueueDispatcher(dispatch_get_main_queue())
internal actual val UIDispatcher: CoroutineDispatcher = ApplicationDispatcher

internal class NsQueueDispatcher(private val dispatchQueue: dispatch_queue_t) : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatchQueue.freeze()) {
            block.run()
        }
    }
}

data class TestData(val testData: TestData?)

internal actual fun test() {
    test1()
    //test2()
}

/*private fun <T,R> async(producer: () -> T, work: (T) -> R) {
    val worker = Worker.start()
    worker.execute(TransferMode.SAFE, producer, work)
    worker.requestTermination()
}*/

private fun test1() {
    val testField = TestData(null)
    val testField1 = TestData(testField)
    val testField2 = TestData(testField1)
    val testField3 = TestData(testField2)
    testField2.freeze()
    assertTrue(testField1.isFrozen)
    assertTrue(testField2.isFrozen)
    assertTrue(testField.isFrozen)
    assertTrue(!testField3.isFrozen, "incoming is frozen")
}

private fun test2() {
    val testFieldout = TestData(null)
    //val testField3 = TestData(testField2)
    val data = DetachedObjectGraph(TransferMode.SAFE) {
        val testField = TestData(null)
        val testField1 = TestData(testField)
        val testField2 = TestData(testField1)
        TestData(testField2)
    }
}
