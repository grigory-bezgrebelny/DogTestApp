package com.bezgrebelnygregory.testapp.app.ui.images

import com.bezgrebelnygregory.testapp.R
import com.bezgrebelnygregory.testapp.app.ui.common.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.module.Module

class ImagesActivity : BaseActivity() {

    override val di: Module
        get() = diModule
    override val layoutRes: Int
        get() = R.layout.activity_images
    override val vm: ImagesVM by viewModel()

}