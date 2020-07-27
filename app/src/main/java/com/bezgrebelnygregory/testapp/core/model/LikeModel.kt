package com.bezgrebelnygregory.testapp.core.model

import com.bezgrebelnygregory.testapp.app.common.IdModel

data class LikeModel(
    override val id: Long,
    val breed: String,
    val image: String
) : IdModel