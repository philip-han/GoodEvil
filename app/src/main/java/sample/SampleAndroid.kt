package sample

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import co.touchlab.viewmodel.GoodEvilViewModel

actual class Sample {
    actual fun checkMe() = 44
}

actual object Platform {
    actual val name: String = "Android"
}

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hello()
        Sample().checkMe()
        setContentView(R.layout.activity_main)
        val viewModel: GoodEvilViewModel = ViewModelProviders.of(this@MainActivity).get(GoodEvilViewModel::class.java)
        viewModel.test { result ->
            Log.i(TAG, "Result: $result")
        }
    }
}