package com.bezgrebelnygregory.testapp.app.common.delegateadapter.item

import android.view.View
import com.bezgrebelnygregory.testapp.R
import com.bezgrebelnygregory.testapp.app.common.delegateadapter.DelegateItem
import com.bezgrebelnygregory.testapp.app.common.delegateadapter.UiModel
import kotlinx.android.synthetic.main.item_breed.view.*

class BreedItem<T>(private var click: ((BreedUIModel<T>) -> Unit)?) :
    DelegateItem<BreedUIModel<T>> {

    override val layoutRes: Int
        get() = R.layout.item_breed

    override fun <T : UiModel> isModel(model: T): Boolean = model is BreedUIModel<*>

    override fun bindView(view: View, data: BreedUIModel<T>) {
        view.tv.text = data.name
    }

    override fun bindClick(view: View, data: BreedUIModel<T>) {
        click?.also { click ->
            view.setOnClickListener {
                click.invoke(data)
            }
        }
    }

    override fun cleanUp() {
        super.cleanUp()
        click = null
    }
}

data class BreedUIModel<T>(
    override val id: Long,
    val name: String,
    val parentModel: T
) : UiModel