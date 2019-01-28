package co.touchlab.kmp.common.api

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal actual val ApplicationDispatcher: CoroutineDispatcher = Dispatchers.Default
internal actual val UIDispatcher: CoroutineDispatcher = Dispatchers.Main
internal actual fun test() {}