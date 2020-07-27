package com.bezgrebelnygregory.testapp.core.model

import com.bezgrebelnygregory.testapp.app.common.IdModel

data class ImageModel(
    override val id: Long,
    val uri: String,
    val isFavorite: Boolean
) : IdModel