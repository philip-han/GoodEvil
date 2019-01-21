package co.touchlab.kmp.common.api

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import platform.darwin.*
import kotlin.coroutines.CoroutineContext

internal actual val ApplicationDispatcher: CoroutineDispatcher = NsQueueDispatcher(dispatch_get_global_queue(0, 0))
internal actual val UIDispatcher: CoroutineDispatcher = NsQueueDispatcher(dispatch_get_main_queue())

/*internal actual val client: HttpClient = HttpClient {
    println("isFrozen ${this.isFrozen}")
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
}*/

internal class NsQueueDispatcher(private val dispatchQueue: dispatch_queue_t) : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatchQueue) {
            block.run()
        }
    }
}

//internal expect val client: HttpClient
internal actual fun parse(json: String): GiphyApi.SearchResult {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}
