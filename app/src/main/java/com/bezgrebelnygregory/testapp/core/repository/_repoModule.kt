package com.bezgrebelnygregory.testapp.core.repository

import com.bezgrebelnygregory.testapp.core.db.AppDB
import org.koin.core.module.Module

fun Module.repoModule() {
    single<BreedRepo> {
        BreedRepoImpl(
            api = get(),
            apiHelper = get(),
            breedRespToBreedModelMapper = get(),
            likeDao = get()
        )
    }
    single<LikeRepo> {
        LikeRepoImpl(
            dao = get<AppDB>().getLikeDao(),
            dbHelper = get(),
            likeModelToLikeEntityMapper = get()
        )
    }
}