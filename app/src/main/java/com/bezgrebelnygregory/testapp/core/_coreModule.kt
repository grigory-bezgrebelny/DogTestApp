package com.bezgrebelnygregory.testapp.core

import com.bezgrebelnygregory.testapp.core.api.apiModule
import com.bezgrebelnygregory.testapp.core.db.dbModule
import com.bezgrebelnygregory.testapp.core.mapper.mapperModule
import com.bezgrebelnygregory.testapp.core.repository.repoModule
import org.koin.core.module.Module
import org.koin.dsl.module

val coreModule: Module
    get() = module {
        apiModule()
        dbModule()
        mapperModule()
        repoModule()
    }