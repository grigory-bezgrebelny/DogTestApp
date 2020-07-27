package com.bezgrebelnygregory.testapp.core.mapper

import com.bezgrebelnygregory.testapp.core.db.entity.LikeEntity
import com.bezgrebelnygregory.testapp.core.model.LikeModel

class LikeModelToLikeEntityMapperImpl : LikeModelToLikeEntityMapper {
    override fun map(from: LikeModel): LikeEntity = LikeEntity(
        id = from.id,
        breed = from.breed,
        image = from.image
    )
}