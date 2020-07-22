package com.bezgrebelnygregory.testapp.app.ui.main

import com.bezgrebelnygregory.testapp.R
import com.bezgrebelnygregory.testapp.app.ui.common.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.module.Module

class MainActivity : BaseActivity() {

    override val di: Module
        get() = diModule
    override val layoutRes: Int
        get() = R.layout.activity_main
    override val vm: MainVM by viewModel()

}