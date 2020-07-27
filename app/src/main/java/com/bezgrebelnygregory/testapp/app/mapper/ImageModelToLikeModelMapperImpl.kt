package com.bezgrebelnygregory.testapp.app.mapper

import com.bezgrebelnygregory.testapp.core.model.ImageModel
import com.bezgrebelnygregory.testapp.core.model.LikeModel

class ImageModelToLikeModelMapperImpl(private val breed: String) : ImageModelToLikeModelMapper {
    override fun map(from: ImageModel): LikeModel = LikeModel(
        id = from.id,
        breed = breed,
        image = from.uri
    )
}