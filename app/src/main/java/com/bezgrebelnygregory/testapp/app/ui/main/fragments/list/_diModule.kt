package com.bezgrebelnygregory.testapp.app.ui.main.fragments.list

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val diModule: Module
    get() = module(override = true) {
        viewModel { ListVM(breedRepo = get()) }
    }