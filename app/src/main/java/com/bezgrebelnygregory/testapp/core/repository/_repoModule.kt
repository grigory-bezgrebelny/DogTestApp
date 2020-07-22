package com.bezgrebelnygregory.testapp.core.repository

import org.koin.core.module.Module

fun Module.repoModule() {
    single<BreedRepo> {
        BreedRepoImpl(
            api = get(),
            apiHelper = get(),
            breedRespToBreedModelMapper = get()
        )
    }
}