package com.bezgrebelnygregory.testapp.app.ui.subbreed

import androidx.lifecycle.viewModelScope
import com.bezgrebelnygregory.testapp.app.ui.common.EventVM
import com.bezgrebelnygregory.testapp.app.ui.model.ApiEvent
import com.bezgrebelnygregory.testapp.core.repository.BreedRepo
import kotlinx.coroutines.launch

class SubBreedVM(
    val content: SubBreedContent,
    private val breedRepo: BreedRepo
) : EventVM<ApiEvent<List<String>>>() {

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            breedRepo.getSubBreeds(content.name) { setEvent(ApiEvent(it)) }
        }
    }
}