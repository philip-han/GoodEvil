package co.touchlab.kmp.common.api

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal actual val ApplicationDispatcher: CoroutineDispatcher = Dispatchers.Default
internal actual val UIDispatcher: CoroutineDispatcher = Dispatchers.Main

//internal expect val client: HttpClient
internal actual fun parse(json: String): GiphyApi.SearchResult {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

