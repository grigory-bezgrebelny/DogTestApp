package com.bezgrebelnygregory.testapp.core.mapper

import com.bezgrebelnygregory.testapp.core.common.Mapper
import com.bezgrebelnygregory.testapp.core.model.BreedModel
import com.bezgrebelnygregory.testapp.core.resp.BreedResp

interface BreedRespToBreedModelMapper : Mapper<BreedResp, List<BreedModel>>