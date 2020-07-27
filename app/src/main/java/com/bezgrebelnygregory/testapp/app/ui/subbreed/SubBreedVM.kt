package com.bezgrebelnygregory.testapp.app.ui.subbreed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bezgrebelnygregory.testapp.app.common.EventVM
import com.bezgrebelnygregory.testapp.core.model.ApiModel
import com.bezgrebelnygregory.testapp.core.repository.BreedRepo
import kotlinx.coroutines.launch

class SubBreedVM(
    val content: SubBreedContent,
    private val breedRepo: BreedRepo
) : EventVM<Event>() {

    private val _dataList = MutableLiveData<List<String>>()
    val dataList: LiveData<List<String>>
        get() = _dataList

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            breedRepo.getSubBreeds(content.breed1) {
                when (it) {
                    is ApiModel.Success -> _dataList.postValue(it.data)
                    is ApiModel.Loading -> setEvent(Event.Loading(it.value))
                    is ApiModel.Error -> setEvent(Event.Error(it.desc))
                }
            }
        }
    }
}