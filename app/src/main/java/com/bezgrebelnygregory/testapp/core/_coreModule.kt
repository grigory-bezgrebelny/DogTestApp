package com.bezgrebelnygregory.testapp.core

import com.bezgrebelnygregory.testapp.core.api.apiModule
import com.bezgrebelnygregory.testapp.core.db.dbModule
import org.koin.core.module.Module
import org.koin.dsl.module

val coreModule: Module
    get() = module {
        apiModule()
        dbModule()
    }