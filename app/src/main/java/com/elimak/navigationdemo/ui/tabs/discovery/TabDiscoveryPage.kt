package com.elimak.navigationdemo.ui.tabs.discovery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.elimak.navigationdemo.App
import com.elimak.navigationdemo.databinding.FragmentTabDiscoveryBinding
import com.elimak.navigationdemo.repository.data.AppState
import com.elimak.navigationdemo.repository.data.ToolBarState
import com.elimak.navigationdemo.ui.base.NavigableFragment

class TabDiscoveryPage : NavigableFragment() {

    private lateinit var viewModel: TabDiscoveryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.injector.inject(this)
        viewModel = ViewModelProvider(this).get(TabDiscoveryViewModel::class.java)

        viewModel.navigationCommand.observe(this, navigationObserver)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentTabDiscoveryBinding.inflate(inflater, container, false).also {
            it.setLifecycleOwner(this)
            it.viewmodel = viewModel
            it.executePendingBindings()
        }.root
    }

    override fun onResume() {
        super.onResume()

        val toolbarState = ToolBarState.Builder()
            .title("Discovery")
            .leftIconResId(ToolBarState.OPTIONS_RES_ID)
            .rightIconResId(ToolBarState.LOGO_RES_ID)
            .build()

        val appState = AppState.Builder()
            .toolBar(true)
            .navigationBar(true)
            .topBarState(toolbarState)
            .build()

        appStateRepository.setState(appState)

        /*
        Redirect to nested Page if nestedPage params was provided
         */
        arguments?.let {
            if(TabDiscoveryPageArgs.fromBundle(arguments!!).nestedStep != null &&
                TabDiscoveryPageArgs.fromBundle(arguments!!).nestedStep == "toNestedPage") {
                view?.let {
                    val action = TabDiscoveryPageDirections.toTabDiscoveryNestedPage()
                    action.deeplinked = true
                    Navigation.findNavController(view!!).navigate(action)
                }
            }
        }
    }
}
