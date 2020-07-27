package com.bezgrebelnygregory.testapp.app.ui.main.fragments.favorites

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bezgrebelnygregory.testapp.R
import com.bezgrebelnygregory.testapp.app.common.delegateadapter.AdapterDelegate
import com.bezgrebelnygregory.testapp.app.common.delegateadapter.UiModel
import com.bezgrebelnygregory.testapp.app.common.delegateadapter.item.BreedItem
import com.bezgrebelnygregory.testapp.app.common.delegateadapter.item.BreedUIModel
import com.bezgrebelnygregory.testapp.app.common.extentions.navigateTo
import com.bezgrebelnygregory.testapp.app.common.extentions.observe
import com.bezgrebelnygregory.testapp.app.common.ui.BaseFragment
import com.bezgrebelnygregory.testapp.app.ui.favoriteimages.FavoriteImagesActivity
import com.bezgrebelnygregory.testapp.app.ui.favoriteimages.FavoriteImagesContent
import com.bezgrebelnygregory.testapp.core.model.BreedModel
import kotlinx.android.synthetic.main.fragment_favorites.view.*
import kotlinx.android.synthetic.main.item_header.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.module.Module

class FavoritesFragment : BaseFragment() {

    override val di: Module
        get() = diModule
    override val layoutRes: Int
        get() = R.layout.fragment_favorites
    override val vm: FavoritesVM by viewModel()

    private val adapter = AdapterDelegate<UiModel>(
        BreedItem<BreedModel> {
            navigateTo<FavoriteImagesActivity>(FavoriteImagesContent(it.parentModel.name))
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupHeader(view)
        setupAdapter(view)
        setupData()
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.cleanUp()
    }

    private fun setupHeader(view: View) {
        view.itemHeader.tvTitle.setText(R.string.favorites)
        view.itemHeader.ivBack.isVisible = false
    }

    private fun setupAdapter(view: View) {
        view.rv.adapter = adapter
        view.rv.layoutManager = LinearLayoutManager(requireContext())
        view.rv.setHasFixedSize(true)
        view.rv.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun setupData() {
        observe(vm.dataList) {
            adapter.dataList = toUiModel(it)
        }
    }

    @SuppressLint("DefaultLocale")
    private fun toUiModel(list: List<BreedModel>): List<UiModel> {
        return list.mapIndexed { i, data ->

            val photosCountText =
                if (data.count > 0) resources.getQuantityString(
                    R.plurals.photos_format,
                    data.count,
                    data.count
                ) else ""

            BreedUIModel(
                i.toLong(),
                (data.name + photosCountText).capitalize(),
                data
            )
        }
    }
}