package com.elimak.navigationdemo.ui.tabs.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.elimak.navigationdemo.App
import com.elimak.navigationdemo.databinding.FramentTabProfileBinding
import com.elimak.navigationdemo.repository.data.AppState
import com.elimak.navigationdemo.repository.data.ToolBarState
import com.elimak.navigationdemo.ui.base.NavigableFragment

class TabProfilePage : NavigableFragment() {

    private lateinit var viewModel: TabProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.injector.inject(this)
        viewModel = ViewModelProvider(this).get(TabProfileViewModel::class.java)

        viewModel.navigationCommand.observe(this, navigationObserver)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FramentTabProfileBinding.inflate(inflater, container, false).also {
            it.setLifecycleOwner(this)
            it.viewmodel = viewModel
            it.executePendingBindings()
        }.root
    }

    override fun onResume() {
        super.onResume()

        val toolbarState = ToolBarState.Builder()
            .title("Profile")
            .leftIconResId(ToolBarState.OPTIONS_RES_ID)
            .rightIconResId(ToolBarState.LOGO_RES_ID)
            .build()

        val appState = AppState.Builder()
            .toolBar(true)
            .navigationBar(true)
            .topBarState(toolbarState)
            .build()

        appStateRepository.setState(appState)

    }

}
