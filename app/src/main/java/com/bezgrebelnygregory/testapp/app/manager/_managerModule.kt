package com.bezgrebelnygregory.testapp.app.manager

import org.koin.core.module.Module

fun Module.managerModule() {
    single<CacheImageFileManager> {
        CacheImageFileManagerImpl(
            context = get()
        )
    }
}