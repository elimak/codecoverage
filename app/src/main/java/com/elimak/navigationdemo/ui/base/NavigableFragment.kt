package com.elimak.navigationdemo.ui.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.elimak.navigationdemo.MainNavigationDirections
import com.elimak.navigationdemo.repository.IAppStateRepository
import com.elimak.navigationdemo.repository.data.ToolBarActionEvent
import com.elimak.navigationdemo.util.Event
import com.elimak.navigationdemo.util.NavigationCommand
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Base Fragment for Fragment that can have a toolbar
 * This will set the default behaviors, but we can override `fun subscribeToToolBarActionChannel`
 * for custom behaviors
 */
@ExperimentalCoroutinesApi
open class NavigableFragment: Fragment() {

    @Inject
    lateinit var appStateRepository: IAppStateRepository

    open var navigationObserver: Observer<Event<NavigationCommand>> = Observer {
        when (val navigationCommand = it.getContentIfNotHandled()) {
            is NavigationCommand.To -> {
                view?.let { Navigation.findNavController(view!!).navigate(navigationCommand.directions) }
            }
            is NavigationCommand.Back -> {
                view?.let { Navigation.findNavController(view!!).navigateUp() }
            }
        }
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToToolBarActionChannel()
    }

    /**
     * can be overridden in descendant classes if wanting to do something else on toolbar actions
     */
    fun subscribeToToolBarActionChannel() {

        lifecycleScope.launch {
            appStateRepository.toolBarActionChannel.consumeEach {
                when(it.type) {
                    ToolBarActionEvent.Actions.SETTINGS_CLICKED -> {
                        val action = MainNavigationDirections.toGlobalSettingsPage()
                        view?.let{Navigation.findNavController(view!!).navigate(action)}
                    }
                    ToolBarActionEvent.Actions.OPTIONS_CLICKED -> {
                        Toast.makeText(context, "Handle the option toolbar icon clicked", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}