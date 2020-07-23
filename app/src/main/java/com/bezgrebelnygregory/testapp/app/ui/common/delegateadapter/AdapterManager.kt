package com.bezgrebelnygregory.testapp.app.ui.common.delegateadapter

import android.view.LayoutInflater
import android.view.ViewGroup

class AdapterManager<U : UiModel>(private vararg val delegateItem: DelegateItem<*>) {

    private lateinit var layoutInflater: LayoutInflater
    private lateinit var currentAdapter: DelegateItem<U>

    // выбор адаптера
    @Suppress("UNCHECKED_CAST")
    fun getItemViewType(model: U): Int {
        if (delegateItem.isEmpty()) throw IllegalArgumentException("Список адаптеров пуст")

        delegateItem.forEachIndexed { index, delegateItem ->
            if (delegateItem.isModel(model)) {
                currentAdapter = delegateItem as DelegateItem<U>
                return index
            }
        }

        throw IllegalArgumentException("Для модели $model отсутствует адаптер")
    }

    /** в данном случае [viewType] это индекс адаптера */
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DelegateViewHolder<U> {
        if (!::layoutInflater.isInitialized) layoutInflater = LayoutInflater.from(parent.context)
        return DelegateViewHolder(
            layoutInflater.inflate(
                currentAdapter.layoutRes,
                parent,
                false
            ),
            currentAdapter
        )
    }

    fun onBindViewHolder(holder: DelegateViewHolder<U>, model: U) {
        currentAdapter.bindView(holder.itemView, model)
        holder.model = model
        holder.bindClick()
    }

    /** для узбежания утечки лучше очищать список адаптеров */
    fun cleanUp() {
        delegateItem.forEach {
            it.cleanUp()
        }
    }
}