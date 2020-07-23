package com.bezgrebelnygregory.testapp.app.ui.common.delegateadapter.item

import android.view.View
import com.bezgrebelnygregory.testapp.R
import com.bezgrebelnygregory.testapp.app.ui.common.delegateadapter.DelegateItem
import com.bezgrebelnygregory.testapp.app.ui.common.delegateadapter.UiModel
import kotlinx.android.synthetic.main.item_breed.view.*

class BreedItem<T>(private var click: ((BreedUIModel<T>) -> Unit)?) :
    DelegateItem<BreedUIModel<T>> {

    override val layoutRes: Int
        get() = R.layout.item_breed

    override fun <T : UiModel> isModel(model: T): Boolean = model is BreedUIModel<*>

    override fun bindView(view: View, data: BreedUIModel<T>) {
        val subBreedCountText =
            if (data.count > 0) view.context.getString(R.string.sub_breed, data.count) else ""
        view.tv.text = data.name + subBreedCountText
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
    val count: Int,
    val parentModel: T
) : UiModel