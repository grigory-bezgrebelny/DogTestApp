package com.bezgrebelnygregory.testapp.app.ui.common.delegateadapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class DelegateViewHolder<U : UiModel>(
    view: View,
    private val currentAdapter: DelegateItem<U>
) : RecyclerView.ViewHolder(view) {

    lateinit var model: U

    fun bindClick() {
        currentAdapter.bindClick(itemView, model)
    }
}