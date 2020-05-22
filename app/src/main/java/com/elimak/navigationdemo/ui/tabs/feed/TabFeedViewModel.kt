package com.elimak.navigationdemo.ui.tabs.feed

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.elimak.navigationdemo.MainNavigationDirections
import com.elimak.navigationdemo.ui.base.AndroidViewModelBase
import com.elimak.navigationdemo.util.Event
import com.elimak.navigationdemo.util.NavigationCommand

class TabFeedViewModel(application: Application) : AndroidViewModelBase(application) {
    val text: MutableLiveData<String> = MutableLiveData("Feed Page in Navigation Tabs\n\n" +
            "Toolbar visible with transparent background\n" +
            "Navigation bar visible\n" +
            "No Navigation Bar background")

    fun goBack() {
        navigationCommand.postValue(Event(NavigationCommand.Back))
    }

    fun goToSettings() {
        // uses global action
        val action = MainNavigationDirections.toGlobalSettingsPage()
        navigationCommand.postValue(Event(NavigationCommand.To(action)))
    }

    fun goToDiscovery() {
        // uses global action
        val action = MainNavigationDirections.toGlobalTabDiscoveryPage()
        navigationCommand.postValue(Event(NavigationCommand.To(action)))
    }

    fun goToDiscoveryNested() {
        // uses global action but add a param so that the Discovery Tab can detect the redirection to the nested page
        val action = MainNavigationDirections.toGlobalTabDiscoveryPage()
        action.nestedStep = "toNestedPage"
        navigationCommand.postValue(Event(NavigationCommand.To(action)))
    }
}
