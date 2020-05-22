package com.elimak.navigationdemo.ui.landing

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.elimak.navigationdemo.MainNavigationDirections
import com.elimak.navigationdemo.ui.base.AndroidViewModelBase
import com.elimak.navigationdemo.util.Event
import com.elimak.navigationdemo.util.NavigationCommand

class LandingViewModel(application: Application) : AndroidViewModelBase(application) {
    val text: MutableLiveData<String> = MutableLiveData("Landing Page \n\n" +
            "No toolbar\n" +
            "No navigation bar")

    fun goToGame() {
        val action = MainNavigationDirections.toGlobalGame()
        navigationCommand.postValue(Event(NavigationCommand.To(action)))
    }

    fun goToProfile() {
        val action = MainNavigationDirections.toGlobalTabProfilePage()
        navigationCommand.postValue(Event(NavigationCommand.To(action)))
    }

    fun goToSettings() {
        val action = MainNavigationDirections.toGlobalSettingsPage()
        navigationCommand.postValue(Event(NavigationCommand.To(action)))
    }
}
