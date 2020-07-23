package com.bezgrebelnygregory.testapp.core.mapper

import com.bezgrebelnygregory.testapp.core.model.BreedModel
import com.bezgrebelnygregory.testapp.core.resp.BreedResp
import kotlin.reflect.full.memberProperties

class BreedRespToBreedModelMapperImpl : BreedRespToBreedModelMapper {
    override fun map(from: BreedResp): List<BreedModel> =
        BreedResp::class.memberProperties.map {
            val size = (it.get(from) as List<*>).size
            BreedModel(it.name.trim(), size)
        }
}