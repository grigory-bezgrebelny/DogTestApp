package com.bezgrebelnygregory.testapp.app.ui.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

abstract class BaseActivity : AppCompatActivity() {

    abstract val di: Module
    abstract val layoutRes: Int
    abstract val vm: ViewModel

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