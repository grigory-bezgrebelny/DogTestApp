package com.bezgrebelnygregory.testapp.app.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.bezgrebelnygregory.testapp.R
import com.bezgrebelnygregory.testapp.app.common.ui.BaseActivity
import com.bezgrebelnygregory.testapp.app.ui.main.fragments.favorites.FavoritesFragment
import com.bezgrebelnygregory.testapp.app.ui.main.fragments.list.ListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.module.Module

class MainActivity : BaseActivity() {

    override val di: Module
        get() = diModule
    override val layoutRes: Int
        get() = R.layout.activity_main
    override val vm: MainVM by viewModel()

    private val navigationItemListener = BottomNavigationView.OnNavigationItemSelectedListener {
        replaceFragment(it.itemId)
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initFragment()
        nav.setOnNavigationItemSelectedListener(navigationItemListener)
    }

    // region navigation
    private fun initFragment() {
        setFragment(vm.selectedFragment)
    }

    private fun replaceFragment(itemId: Int) {
        if (itemId != vm.selectedFragment) {
            vm.selectedFragment = itemId
            setFragment(itemId)
        }
    }

    private fun setFragment(itemId: Int) {
        val fragment: Fragment = getFragment(itemId)
        supportFragmentManager.commit {
            setCustomAnimations(R.anim.fragment_fade_enter, R.anim.fragment_fade_exit)
            replace(fragmentContainer.id, fragment)
        }
    }

    private fun getFragment(itemId: Int) = when (itemId) {
        R.id.action_breed_list -> ListFragment()
        R.id.action_favorites -> FavoritesFragment()
        else -> throw IllegalArgumentException()
    }
    // endregion
}