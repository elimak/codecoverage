package com.elimak.navigationdemo.ui.tabs.discovery

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.elimak.navigationdemo.ui.base.AndroidViewModelBase
import com.elimak.navigationdemo.util.Event
import com.elimak.navigationdemo.util.NavigationCommand

class TabDiscoveryViewModel(application: Application) : AndroidViewModelBase(application) {
    val text: MutableLiveData<String> = MutableLiveData("Tab Discovery Page\n\n" +
            "Toolbar visible\n" +
            "Navigation bar visible\n" +
            "Navigation Bar background visible")

    fun goBack() {
        navigationCommand.postValue(Event(NavigationCommand.Back))
    }

    fun goToNestedPage() {
        val action = TabDiscoveryPageDirections.toTabDiscoveryNestedPage()
        navigationCommand.postValue(Event(NavigationCommand.To(action)))
    }
}
