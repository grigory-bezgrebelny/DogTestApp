package com.bezgrebelnygregory.testapp.core.mapper

import org.koin.core.module.Module

fun Module.mapperModule() {
    single<BreedRespToBreedModelMapper> { BreedRespToBreedModelMapperImpl() }
}