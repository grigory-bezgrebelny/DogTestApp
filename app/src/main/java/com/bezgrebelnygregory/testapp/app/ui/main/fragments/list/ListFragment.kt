package com.bezgrebelnygregory.testapp.app.ui.main.fragments.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bezgrebelnygregory.testapp.R
import com.bezgrebelnygregory.testapp.app.ui.common.BaseFragment
import com.bezgrebelnygregory.testapp.app.ui.common.delegateadapter.AdapterDelegate
import com.bezgrebelnygregory.testapp.app.ui.common.delegateadapter.UiModel
import com.bezgrebelnygregory.testapp.app.ui.common.delegateadapter.item.BreedItem
import com.bezgrebelnygregory.testapp.app.ui.common.delegateadapter.item.BreedUIModel
import com.bezgrebelnygregory.testapp.app.ui.common.extentions.navigateTo
import com.bezgrebelnygregory.testapp.app.ui.common.extentions.observeEvent
import com.bezgrebelnygregory.testapp.app.ui.images.ImagesActivity
import com.bezgrebelnygregory.testapp.app.ui.images.ImagesContent
import com.bezgrebelnygregory.testapp.app.ui.subbreed.SubBreedActivity
import com.bezgrebelnygregory.testapp.app.ui.subbreed.SubBreedContent
import com.bezgrebelnygregory.testapp.core.model.ApiModel
import com.bezgrebelnygregory.testapp.core.model.BreedModel
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.item_header.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.module.Module

class ListFragment : BaseFragment() {

    override val di: Module
        get() = diModule
    override val layoutRes: Int
        get() = R.layout.fragment_list
    override val vm: ListVM by viewModel()

    private val adapter = AdapterDelegate<UiModel>(
        BreedItem<BreedModel> {
            if (it.parentModel.count > 0) navigateTo<SubBreedActivity>(SubBreedContent(it.parentModel.name))
            else navigateTo<ImagesActivity>(ImagesContent(it.parentModel.name))
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupHeader(view)
        setupView(view)
        setupAdapter(view)
        setupEvent(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.cleanUp()
    }

    private fun setupHeader(view: View) {
        view.itemHeader.tvTitle.setText(R.string.breed)
        view.itemHeader.ivBack.isVisible = false
    }

    private fun setupView(view: View) {
        view.btnRefresh.setOnClickListener {
            vm.fetchData()
        }
    }

    private fun setupAdapter(view: View) {
        view.rv.adapter = adapter
        view.rv.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupEvent(view: View) {
        observeEvent(vm.event) {
            when (it.event) {
                is ApiModel.Success -> adapter.dataList = toUiModel(it.event.data)
                is ApiModel.Loading -> {
                    if (it.event.value) view.btnRefresh.isVisible = false
                    view.pg.isVisible = it.event.value
                }
                is ApiModel.Error -> {
                    view.btnRefresh.isVisible = true
                    showErrorAlert(it.event.desc)
                }
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private fun toUiModel(list: List<BreedModel>): List<UiModel> {
        return list.mapIndexed { i, data ->
            BreedUIModel(
                i.toLong(),
                data.name.capitalize(),
                data.count,
                data
            )
        }
    }
}