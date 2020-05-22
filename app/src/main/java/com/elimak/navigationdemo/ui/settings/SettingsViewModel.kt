package com.elimak.navigationdemo.ui.settings

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.elimak.navigationdemo.ui.base.AndroidViewModelBase
import com.elimak.navigationdemo.util.Event
import com.elimak.navigationdemo.util.NavigationCommand

class SettingsViewModel(application: Application) : AndroidViewModelBase(application) {
    val text: MutableLiveData<String> = MutableLiveData("Settings Page\n\n" +
            "Toolbar visible\n" +
            "No Navigation bar")

    fun goBack() {
        navigationCommand.postValue(Event(NavigationCommand.Back))
    }

}
