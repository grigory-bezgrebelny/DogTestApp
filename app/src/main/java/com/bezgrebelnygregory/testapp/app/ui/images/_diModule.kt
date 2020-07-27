package com.bezgrebelnygregory.testapp.app.ui.images

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val diModule: Module
    get() = module {
        viewModel { (content: ImagesContent) ->
            ImagesVM(
                content = content,
                breedRepo = get(),
                likeRepo = get(),
                cacheImageFileManager = get()
            )
        }
    }