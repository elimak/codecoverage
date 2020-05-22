package com.elimak.navigationdemo.ui.tabs.discovery.nested

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.elimak.navigationdemo.ui.base.AndroidViewModelBase
import com.elimak.navigationdemo.util.Event
import com.elimak.navigationdemo.util.NavigationCommand

class TabDiscoveryNestedViewModel(application: Application) : AndroidViewModelBase(application) {
    val text: MutableLiveData<String> = MutableLiveData("Tab Discovery Nested Page\n\n" +
            "Toolbar visible\n" +
            "Navigation bar visible\n" +
            "Navigation Bar background visible\n"+
            "SHOULD HAVE Discovery Tab highlighted")

    val deeplinked = MutableLiveData<Boolean>(false)

    fun goBack() {
        navigationCommand.postValue(Event(NavigationCommand.Back))
    }
}
