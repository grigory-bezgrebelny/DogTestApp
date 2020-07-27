package com.bezgrebelnygregory.testapp.app.ui.main.fragments.favorites

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.bezgrebelnygregory.testapp.core.repository.LikeRepo

class FavoritesVM(
    private val likeRepo: LikeRepo
) : ViewModel() {

    val dataList = Transformations.map(likeRepo.getBreedList()) { it }

}