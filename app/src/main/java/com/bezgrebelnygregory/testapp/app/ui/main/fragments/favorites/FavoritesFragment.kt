package com.bezgrebelnygregory.testapp.app.ui.main.fragments.favorites

import android.os.Bundle
import android.view.View
import com.bezgrebelnygregory.testapp.R
import com.bezgrebelnygregory.testapp.app.common.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.module.Module

class FavoritesFragment : BaseFragment() {

    override val di: Module
        get() = diModule
    override val layoutRes: Int
        get() = R.layout.fragment_favorites
    override val vm: FavoritesVM by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}