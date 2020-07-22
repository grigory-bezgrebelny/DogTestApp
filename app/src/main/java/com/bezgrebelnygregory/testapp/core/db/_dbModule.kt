package com.bezgrebelnygregory.testapp.core.db

import com.bezgrebelnygregory.testapp.core.db.dao.daoModule
import org.koin.core.module.Module

fun Module.dbModule() {
    daoModule()
    single { AppDB.create(context = get()) }
    single { DbHelper() }
}