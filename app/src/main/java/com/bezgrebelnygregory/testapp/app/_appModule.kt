package com.bezgrebelnygregory.testapp.app

import com.bezgrebelnygregory.testapp.app.manager.managerModule
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module
    get() = module {
        managerModule()
    }