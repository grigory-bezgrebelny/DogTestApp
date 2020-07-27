package com.bezgrebelnygregory.testapp.core.api

import com.bezgrebelnygregory.testapp.core.api.resp.BreedResp
import com.bezgrebelnygregory.testapp.core.api.resp.DataResp
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("breed/{breed}/images")
    suspend fun getImages(@Path("breed") breed: String): DataResp<List<String>>

    @GET("breed/{breed}/list")
    suspend fun getSubBreeds(@Path("breed") breed: String): DataResp<List<String>>

    @GET("breed/{breed1}/{breed2}/images")
    suspend fun getSubBreedsImages(
        @Path("breed1") breed1: String,
        @Path("breed2") breed2: String
    ): DataResp<List<String>>

    @GET("breeds/list/all")
    suspend fun getBreeds(): DataResp<BreedResp>
}