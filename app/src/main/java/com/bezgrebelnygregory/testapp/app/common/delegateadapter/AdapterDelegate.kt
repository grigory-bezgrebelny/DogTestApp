package com.bezgrebelnygregory.testapp.app.common.delegateadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdapterDelegate<U>(vararg delegateItem: DelegateItem<*>) :
    RecyclerView.Adapter<DelegateViewHolder<U>>() where U : UiModel {

    private val adapterManager = AdapterManager<U>(*delegateItem)

    var dataList: List<U> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    init {
        this.setHasStableIds(true)
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemId(position: Int): Long = dataList[position].id

    // выбор адаптера
    @Suppress("UNCHECKED_CAST")
    override fun getItemViewType(position: Int): Int =
        adapterManager.getItemViewType(dataList[position])

    /** в данном случае [viewType] это индекс адаптера */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DelegateViewHolder<U> =
        adapterManager.onCreateViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: DelegateViewHolder<U>, position: Int) {
        adapterManager.onBindViewHolder(holder, dataList[position])
    }

    fun cleanUp() {
        adapterManager.cleanUp()
    }
}