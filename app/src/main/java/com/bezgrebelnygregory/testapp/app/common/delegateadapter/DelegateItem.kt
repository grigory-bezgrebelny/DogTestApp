package com.bezgrebelnygregory.testapp.app.common.delegateadapter

import android.view.View

interface DelegateItem<T : UiModel> {
    val layoutRes: Int
    fun <T : UiModel> isModel(model: T): Boolean
    fun bindView(view: View, data: T)
    fun bindClick(view: View, data: T)
    fun cleanUp() = Unit
}