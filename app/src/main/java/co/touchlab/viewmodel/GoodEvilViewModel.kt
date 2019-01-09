package co.touchlab.viewmodel

import android.arch.lifecycle.ViewModel
import co.touchlab.kmp.common.api.GiphyApi

class GoodEvilViewModel: ViewModel() {
    val api: GiphyApi = GiphyApi()

    fun test(callback: (String) -> Unit) {
        api.good(callback)
    }
}