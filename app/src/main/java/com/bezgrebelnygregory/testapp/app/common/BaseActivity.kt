package com.bezgrebelnygregory.testapp.app.common

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

abstract class BaseActivity : AppCompatActivity(), BaseView, ContextAware {

    override val currentContext: Context
        get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        loadKoinModules(di)
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(di)
    }
}