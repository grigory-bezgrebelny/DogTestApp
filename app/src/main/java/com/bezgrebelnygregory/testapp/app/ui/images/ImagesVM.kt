package com.bezgrebelnygregory.testapp.app.ui.images

import androidx.lifecycle.viewModelScope
import com.bezgrebelnygregory.testapp.app.common.EventVM
import com.bezgrebelnygregory.testapp.app.manager.CacheImageFileManager
import com.bezgrebelnygregory.testapp.app.mapper.ImageModelToLikeModelMapperImpl
import com.bezgrebelnygregory.testapp.core.model.ApiModel
import com.bezgrebelnygregory.testapp.core.model.ImageModel
import com.bezgrebelnygregory.testapp.core.repository.BreedRepo
import com.bezgrebelnygregory.testapp.core.repository.LikeRepo
import kotlinx.coroutines.launch

class ImagesVM(
    val content: ImagesContent,
    breedRepo: BreedRepo,
    private val likeRepo: LikeRepo,
    private val cacheImageFileManager: CacheImageFileManager
) : EventVM<Event>() {

    private val dataSource = breedRepo.getImages(viewModelScope, content.name) {
        when (it) {
            is ApiModel.Loading -> setEvent(Event.Loading(it.value))
            is ApiModel.Error -> setEvent(Event.Error(it.desc))
        }
    }
    val dataList = dataSource.dataList

    fun like(data: ImageModel) {
        viewModelScope.launch {
            val model = ImageModelToLikeModelMapperImpl(content.name).map(data)
            if (data.idNotSet) likeRepo.insert(model)
            else likeRepo.delete(model)
        }
    }

    fun fetchData() {
        dataSource.fetchData()
    }

    fun getImageUri(position: Int) {
        viewModelScope.launch {
            val list = dataList.value ?: listOf()
            val uri = if (list.lastIndex >= position) {
                cacheImageFileManager.createAndGetUri(list[position].uri)
            } else null
            setEvent(Event.Share(uri))
        }
    }
}