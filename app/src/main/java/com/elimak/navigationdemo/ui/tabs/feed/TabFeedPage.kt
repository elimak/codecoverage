package com.elimak.navigationdemo.ui.tabs.feed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.elimak.navigationdemo.App
import com.elimak.navigationdemo.databinding.FragmentTabFeedBinding
import com.elimak.navigationdemo.repository.data.AppState
import com.elimak.navigationdemo.repository.data.ToolBarState
import com.elimak.navigationdemo.ui.base.NavigableFragment
import com.google.gson.Gson

class TabFeedPage : NavigableFragment() {

    private lateinit var viewModel: TabFeedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.injector.inject(this)
        viewModel = ViewModelProvider(this).get(TabFeedViewModel::class.java)

        viewModel.navigationCommand.observe(this, navigationObserver)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentTabFeedBinding.inflate(inflater, container, false).also {
            it.setLifecycleOwner(this)
            it.viewmodel = viewModel
            it.executePendingBindings()
        }.root
    }

    override fun onResume() {
        super.onResume()

        val toolbarState = ToolBarState.Builder()
            .leftIconResId(ToolBarState.OPTIONS_RES_ID)
            .rightIconResId(ToolBarState.LOGO_RES_ID)
            .build()

        val appState = AppState.Builder()
            .toolBar(true)
            .toolBarBackground(false)
            .navigationBar(true)
            .navigationBarBackground(false)
            .topBarState(toolbarState)
            .build()

        Log.d("val", Gson().toJson(appState))

        appStateRepository.setState(appState)
    }
}
