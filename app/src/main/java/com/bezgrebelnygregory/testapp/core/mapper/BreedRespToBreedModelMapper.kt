package com.bezgrebelnygregory.testapp.core.mapper

import com.bezgrebelnygregory.testapp.core.api.resp.BreedResp
import com.bezgrebelnygregory.testapp.core.common.Mapper
import com.bezgrebelnygregory.testapp.core.model.BreedModel

interface BreedRespToBreedModelMapper : Mapper<BreedResp, List<BreedModel>>