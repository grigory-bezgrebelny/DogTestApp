package com.bezgrebelnygregory.testapp.app.ui.subbreed

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bezgrebelnygregory.testapp.R
import com.bezgrebelnygregory.testapp.app.common.BaseActivity
import com.bezgrebelnygregory.testapp.app.common.delegateadapter.AdapterDelegate
import com.bezgrebelnygregory.testapp.app.common.delegateadapter.UiModel
import com.bezgrebelnygregory.testapp.app.common.delegateadapter.item.BreedItem
import com.bezgrebelnygregory.testapp.app.common.delegateadapter.item.BreedUIModel
import com.bezgrebelnygregory.testapp.app.common.extentions.getActivityContent
import com.bezgrebelnygregory.testapp.app.common.extentions.navigateTo
import com.bezgrebelnygregory.testapp.app.common.extentions.observeEvent
import com.bezgrebelnygregory.testapp.app.ui.images.ImagesActivity
import com.bezgrebelnygregory.testapp.app.ui.images.ImagesContent
import com.bezgrebelnygregory.testapp.core.model.ApiModel
import kotlinx.android.synthetic.main.activity_sub_breed.*
import kotlinx.android.synthetic.main.item_header.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf

class SubBreedActivity : BaseActivity() {

    override val di: Module
        get() = diModule
    override val layoutRes: Int
        get() = R.layout.activity_sub_breed
    override val vm: SubBreedVM by viewModel { parametersOf(getActivityContent()) }

    private val adapter = AdapterDelegate<UiModel>(
        BreedItem<String> {
            navigateTo<ImagesActivity>(ImagesContent(vm.content.breed1, it.parentModel))
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupHeader()
        setupView()
        setupAdapter()
        setupEvent()
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.cleanUp()
    }

    @SuppressLint("DefaultLocale")
    private fun setupHeader() {
        itemHeader.tvTitle.text = vm.content.breed1.capitalize()
        itemHeader.ivBack.setOnClickListener { onBackPressed() }
    }

    private fun setupView() {
        btnRefresh.setOnClickListener {
            vm.fetchData()
        }
    }

    private fun setupAdapter() {
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)
        rv.setHasFixedSize(true)
        rv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    private fun setupEvent() {
        observeEvent(vm.event) {
            when (it.event) {
                is ApiModel.Success -> adapter.dataList = toUiModel(it.event.data)
                is ApiModel.Loading -> {
                    if (it.event.value) btnRefresh.isVisible = false
                    pg.isVisible = it.event.value
                }
                is ApiModel.Error -> {
                    btnRefresh.isVisible = true
                    showErrorAlert(it.event.desc)
                }
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private fun toUiModel(list: List<String>): List<UiModel> {
        return list.mapIndexed { i, data ->
            BreedUIModel(
                i.toLong(),
                data.capitalize(),
                data
            )
        }
    }
}