package co.touchlab.kmp.common.api

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.http.Url
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

internal expect val ApplicationDispatcher: CoroutineDispatcher

expect fun createHttpClient(): HttpClient

class GiphyApi {

    val client: HttpClient = createHttpClient()
    var address = "http://api.giphy.com/v1/gifs/search"
    var goodAddr = Url("$address?q=awesome")
    var evilAddr = Url("$address?q=redsox")

    fun good(callback: (String) -> Unit) {
        callBackend(goodAddr, callback)
    }

    fun evil(callback: (String) -> Unit) {
        callBackend(evilAddr, callback)
    }

    private fun callBackend(addr: Url, callback: (String) -> Unit) {
        println("api: $addr")
        GlobalScope.apply {
            launch(ApplicationDispatcher) {
                val result: String = client.get {
                    url(addr.toString())
                    header("api-key", "dc6zaTOxFJmzC")
                }
                callback(result)
            }
        }
    }
}