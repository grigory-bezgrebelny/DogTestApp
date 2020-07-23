package com.bezgrebelnygregory.testapp.app.ui.common

import androidx.lifecycle.ViewModel
import org.koin.core.module.Module

interface BaseView {
    val di: Module
    val layoutRes: Int
    val vm: ViewModel
}