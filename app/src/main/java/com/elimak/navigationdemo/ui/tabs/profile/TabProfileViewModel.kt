package com.elimak.navigationdemo.ui.tabs.profile

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.elimak.navigationdemo.ui.base.AndroidViewModelBase
import com.elimak.navigationdemo.util.Event
import com.elimak.navigationdemo.util.NavigationCommand

class TabProfileViewModel(application: Application) : AndroidViewModelBase(application) {
    val text: MutableLiveData<String> = MutableLiveData("Profile Page in Navigation Tabs\n\n" +
            "Toolbar visible\n" +
            "Navigation bar visible\n" +
            "Navigation Bar background visible")

    fun goBack() {
        navigationCommand.postValue(Event(NavigationCommand.Back))
    }
}
