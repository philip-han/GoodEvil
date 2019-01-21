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
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JSON

internal expect val ApplicationDispatcher: CoroutineDispatcher
internal expect val UIDispatcher: CoroutineDispatcher
//internal expect val client: HttpClient
internal expect fun parse(json: String): GiphyApi.SearchResult

class GiphyApi {

    val client: HttpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(JSON.nonstrict)
        }
    }
    var address = "https://api.giphy.com/v1/gifs/search"
    var goodAddr = Url("$address?q=awesome")
    var evilAddr = Url("$address?q=redsox")

    fun good(callback: (SearchResult) -> Unit) {
        callBackend(goodAddr, callback)
    }

    fun evil(callback: (SearchResult) -> Unit) {
        callBackend(evilAddr, callback)
    }

    @Serializable
    data class SearchResult(val data: List<Data>?)

    @Serializable
    data class Data(val type: String, val id: String, val url: String, val images: Images)

    @Serializable
    data class Images(
        @SerialName("fixed_width_downsampled")
        val fixedWidthDownSampled: FixedWidthDownsampled
    )

    @Serializable
    data class FixedWidthDownsampled(val url: String, val webp: String)

    private fun callBackend(addr: Url, callback: (SearchResult) -> Unit) {
        GlobalScope.apply {
            launch(ApplicationDispatcher) {
                println("inside async")
                val result: String = client.get {
                    url(addr.toString())
                    header("api-key", "dc6zaTOxFJmzC")
                }
                val json = JSON.nonstrict.parse(SearchResult.serializer(), result)
                println("result: $json")
                UIDispatcher.dispatch(coroutineContext,
                    object : Runnable {
                        override fun run() {
                            callback(json)
                        }
                    }
                )
            }
        }
    }

}