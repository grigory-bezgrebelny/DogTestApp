package com.bezgrebelnygregory.testapp.app.common.delegateadapter.item

import android.view.View
import com.bezgrebelnygregory.testapp.R
import com.bezgrebelnygregory.testapp.app.common.delegateadapter.DelegateItem
import com.bezgrebelnygregory.testapp.app.common.delegateadapter.UiModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.item_image.view.*

class ImageItem<T>(private var click: ((ImageUiModel<T>) -> Unit)?) :
    DelegateItem<ImageUiModel<T>> {

    override val layoutRes: Int
        get() = R.layout.item_image

    override fun <T : UiModel> isModel(model: T): Boolean = model is ImageUiModel<*>

    override fun bindView(view: View, data: ImageUiModel<T>) {
        Glide.with(view.context)
            .load(data.uri)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view.iv)

        view.ivLike.setImageResource(if (data.isFavorite) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24)
    }

    override fun bindClick(view: View, data: ImageUiModel<T>) {
        click?.also { click ->
            view.ivLike.setOnClickListener {
                click.invoke(data)
            }
        }
    }

    override fun cleanUp() {
        super.cleanUp()
        click = null
    }
}

data class ImageUiModel<T>(
    override val id: Long,
    val uri: String,
    val isFavorite: Boolean,
    val parentModel: T
) : UiModel