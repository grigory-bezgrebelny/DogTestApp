package com.bezgrebelnygregory.testapp.app.ui.main.fragments.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bezgrebelnygregory.testapp.app.common.EventVM
import com.bezgrebelnygregory.testapp.core.model.ApiModel
import com.bezgrebelnygregory.testapp.core.model.BreedModel
import com.bezgrebelnygregory.testapp.core.repository.BreedRepo
import kotlinx.coroutines.launch

class ListVM(private val breedRepo: BreedRepo) : EventVM<Event>() {

    private val _dataList = MutableLiveData<List<BreedModel>>()
    val dataList: LiveData<List<BreedModel>>
        get() = _dataList

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            breedRepo.getBreeds {
                when (it) {
                    is ApiModel.Success -> _dataList.postValue(it.data)
                    is ApiModel.Loading -> setEvent(Event.Loading(it.value))
                    is ApiModel.Error -> setEvent(Event.Error(it.desc))
                }
            }
        }
    }
}