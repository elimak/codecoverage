package com.elimak.navigationdemo.ui.components.toolbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.elimak.navigationdemo.App
import com.elimak.navigationdemo.databinding.FragmentToolbarBinding
import dagger.MembersInjector
import javax.inject.Inject

class ToolBarFragment : Fragment() {

    private lateinit var viewModel: ToolBarViewModel
    @Inject lateinit var memberInjector: MembersInjector<ToolBarViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.injector.inject(this)

        viewModel = ViewModelProvider(this).get(ToolBarViewModel::class.java)
        memberInjector.injectMembers(viewModel)
        viewModel.postInjectionInit()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentToolbarBinding.inflate(inflater, container, false).also {
            it.setLifecycleOwner(this)
            it.viewModel = viewModel
            it.executePendingBindings()
        }.root
    }
}
