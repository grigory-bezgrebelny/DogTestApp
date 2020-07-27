package com.bezgrebelnygregory.testapp.app.ui.favoriteimages

import androidx.lifecycle.viewModelScope
import com.bezgrebelnygregory.testapp.app.common.EventVM
import com.bezgrebelnygregory.testapp.app.manager.CacheImageFileManager
import com.bezgrebelnygregory.testapp.app.mapper.ImageModelToLikeModelMapperImpl
import com.bezgrebelnygregory.testapp.core.model.ImageModel
import com.bezgrebelnygregory.testapp.core.repository.LikeRepo
import kotlinx.coroutines.launch

class FavoriteImagesVM(
    val content: FavoriteImagesContent,
    private val likeRepo: LikeRepo,
    private val cacheImageFileManager: CacheImageFileManager
) : EventVM<Event>() {

    private val dataSource = likeRepo.getListByBreed(viewModelScope, content.name)
    val dataList = dataSource.dataList

    fun like(data: ImageModel) {
        viewModelScope.launch {
            val model = ImageModelToLikeModelMapperImpl(content.name).map(data)
            if (data.isFavorite) likeRepo.delete(model)
            else likeRepo.insert(model)
        }
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