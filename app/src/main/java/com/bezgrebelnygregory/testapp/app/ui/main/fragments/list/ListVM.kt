package com.bezgrebelnygregory.testapp.app.ui.main.fragments.list

import androidx.lifecycle.viewModelScope
import com.bezgrebelnygregory.testapp.app.common.EventVM
import com.bezgrebelnygregory.testapp.app.model.ApiEvent
import com.bezgrebelnygregory.testapp.core.model.BreedModel
import com.bezgrebelnygregory.testapp.core.repository.BreedRepo
import kotlinx.coroutines.launch

class ListVM(private val breedRepo: BreedRepo) : EventVM<ApiEvent<List<BreedModel>>>() {

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            breedRepo.getBreeds { setEvent(ApiEvent(it)) }
        }
    }
}