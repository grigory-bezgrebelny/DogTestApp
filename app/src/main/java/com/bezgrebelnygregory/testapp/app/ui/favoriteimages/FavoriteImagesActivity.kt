package com.bezgrebelnygregory.testapp.app.ui.favoriteimages

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bezgrebelnygregory.testapp.R
import com.bezgrebelnygregory.testapp.app.common.delegateadapter.AdapterDelegate
import com.bezgrebelnygregory.testapp.app.common.delegateadapter.UiModel
import com.bezgrebelnygregory.testapp.app.common.delegateadapter.item.ImageItem
import com.bezgrebelnygregory.testapp.app.common.delegateadapter.item.ImageUiModel
import com.bezgrebelnygregory.testapp.app.common.extentions.getActivityContent
import com.bezgrebelnygregory.testapp.app.common.extentions.observe
import com.bezgrebelnygregory.testapp.app.common.extentions.observeEvent
import com.bezgrebelnygregory.testapp.app.common.ui.BaseActivity
import com.bezgrebelnygregory.testapp.core.model.ImageModel
import kotlinx.android.synthetic.main.activity_favorite_images.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf

class FavoriteImagesActivity : BaseActivity() {

    override val di: Module
        get() = diModule
    override val layoutRes: Int
        get() = R.layout.activity_favorite_images
    override val vm: FavoriteImagesVM by viewModel { parametersOf(getActivityContent()) }


    private val adapter = AdapterDelegate<UiModel>(
        ImageItem<ImageModel> { vm.like(it.parentModel) }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupHeader()
        setupAdapter()
        setupView()
        setupData()
        setupEvent()
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.cleanUp()
    }

    @SuppressLint("DefaultLocale")
    private fun setupHeader() {
        tvTitle.text = vm.content.name.capitalize()
        ivBack.setOnClickListener { onBackPressed() }
    }

    private fun setupAdapter() {
        val sh = PagerSnapHelper()
        sh.attachToRecyclerView(rv)

        rv.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rv.adapter = adapter
        rv.setHasFixedSize(true)
    }

    private fun setupView() {
        ivShare.setOnClickListener {
            share()
        }
    }

    private fun share() {
        val position = (rv.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        vm.getImageUri(position)
    }

    private fun setupData() {
        observe(vm.dataList) {
            adapter.dataList = toUiModel(it)
            tvListIsEmpty.isVisible = it.isEmpty()
        }
    }

    private fun setupEvent() {
        observeEvent(vm.event) {
            when (it) {
                is Event.Share -> {
                    if (it.uri != null) showShareImageDialog(it.uri)
                    else showErrorAlert(getString(R.string.image_not_found))
                }
            }
        }
    }

    private fun toUiModel(list: List<ImageModel>): List<UiModel> = list.mapIndexed { i, data ->
        ImageUiModel(i.toLong(), data.uri, data.isFavorite, data)
    }
}