package com.elimak.navigationdemo.ui.tabs.notification

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.elimak.navigationdemo.ui.base.AndroidViewModelBase
import com.elimak.navigationdemo.util.Event
import com.elimak.navigationdemo.util.NavigationCommand

class TabNotificationViewModel(application: Application) : AndroidViewModelBase(application) {
    val text: MutableLiveData<String> = MutableLiveData("Notification Page in Navigation Tabs\n\n" +
            "Toolbar visible\n" +
            "Navigation bar visible\n" +
            "Navigation Bar background visible")

    val placeholder: MutableLiveData<String> = MutableLiveData("Lorem ipsum dolor sit amet, consectetur  \n adipiscing elit, sed do eiusmod tempo" +
            "r incididunt ut labore et dolore magna aliqua. Ut enim ad \n \n" +
            "minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex \n \n" +
            "ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum d \n\n" +
            "olore eu fugiat nulla pariatur. Lorem ipsum dolor amet mustache knausgaard +1, " +
            "blue bottle waistcoat tbh semiotics artisan synth stumptown gastropub cornhole ce \n\n" +
            "liac swag. Brunch raclette vexillologist post-ironic glossier ennui XOXO mlkshk \n" +
            "godard pour-over blog tumblr humblebrag. Blue bottle put a bird on it twee  \n" +
            " prism biodiesel brooklyn. Blue bottle ennui tbh succulents.")

    fun goBack() {
        navigationCommand.postValue(Event(NavigationCommand.Back))
    }
}
