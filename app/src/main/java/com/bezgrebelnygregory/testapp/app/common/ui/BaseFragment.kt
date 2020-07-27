package com.bezgrebelnygregory.testapp.app.common.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bezgrebelnygregory.testapp.app.common.ContextAware
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

abstract class BaseFragment : Fragment(),
    BaseView,
    ContextAware {

    override val currentContext: Context
        get() = requireContext()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loadKoinModules(di)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutRes, container, false)

    override fun onDetach() {
        super.onDetach()
        unloadKoinModules(di)
    }
}