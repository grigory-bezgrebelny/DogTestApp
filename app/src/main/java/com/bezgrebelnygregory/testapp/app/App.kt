package com.bezgrebelnygregory.testapp.app

import androidx.multidex.MultiDexApplication
import com.bezgrebelnygregory.testapp.core.coreModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@Suppress("unused")
class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(coreModule, appModule))
        }
    }
}