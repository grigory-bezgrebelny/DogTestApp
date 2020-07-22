package com.bezgrebelnygregory.testapp.app.ui.subbreed

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val diModule: Module
    get() = module {
        viewModel {
            SubBreedVM()
        }
    }