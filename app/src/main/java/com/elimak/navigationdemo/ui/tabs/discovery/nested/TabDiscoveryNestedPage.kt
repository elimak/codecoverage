package com.elimak.navigationdemo.ui.tabs.discovery.nested

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.elimak.navigationdemo.App
import com.elimak.navigationdemo.databinding.FragmentTabDiscoveryNestedBinding
import com.elimak.navigationdemo.repository.data.AppState
import com.elimak.navigationdemo.repository.data.ToolBarState
import com.elimak.navigationdemo.ui.base.NavigableFragment
import com.elimak.navigationdemo.util.Event
import com.elimak.navigationdemo.util.NavigationCommand
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class TabDiscoveryNestedPage : NavigableFragment() {

    private lateinit var viewModel: TabDiscoveryNestedViewModel

    override var navigationObserver: Observer<Event<NavigationCommand>> = Observer {
        when (val navigationCommand = it.getContentIfNotHandled()) {
            is NavigationCommand.To -> {
                view?.let { Navigation.findNavController(view!!).navigate(navigationCommand.directions) }
            }
            is NavigationCommand.Back -> {
                view?.let {
                    if(viewModel.deeplinked.value!!) {
                        Navigation.findNavController(view!!).popBackStack()
                    }
                    Navigation.findNavController(view!!).navigateUp()
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.injector.inject(this)
        viewModel = ViewModelProvider(this).get(TabDiscoveryNestedViewModel::class.java)

        viewModel.navigationCommand.observe(this, navigationObserver)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentTabDiscoveryNestedBinding.inflate(inflater, container, false).also {
            it.setLifecycleOwner(this)
            it.viewmodel = viewModel
            it.executePendingBindings()
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            viewModel.deeplinked.value = TabDiscoveryNestedPageArgs.fromBundle(arguments!!).deeplinked
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.d("val", "Fragment back pressed invoked " + view)
                    view?.let{viewModel.goBack()}
                }
            }
        )
    }

    override fun onResume() {
        super.onResume()
        val toolbarState = ToolBarState.Builder()
            .title("Discovery Nested Page")
            .leftIconResId(ToolBarState.LOGO_RES_ID)
            .build()

        val appState = AppState.Builder()
            .toolBar(true)
            .navigationBar(true)
            .topBarState(toolbarState)
            .build()

        appStateRepository.setState(appState)

    }
}
