package com.example.weather.bases

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    abstract fun componentInject()

    abstract fun componentRelease()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        componentInject()
    }

    override fun onDestroy() {
        componentRelease()
        super.onDestroy()
    }
}