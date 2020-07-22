package com.bezgrebelnygregory.testapp.core.db.dao

import com.bezgrebelnygregory.testapp.core.db.AppDB
import org.koin.core.module.Module

fun Module.daoModule() {
    single { get<AppDB>().getLikeDao() }
}