package com.elimak.navigationdemo.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.elimak.navigationdemo.util.Event
import com.elimak.navigationdemo.util.NavigationCommand

open class  AndroidViewModelBase(application: Application) : AndroidViewModel(application) {
    val navigationCommand: MutableLiveData<Event<NavigationCommand>> = MutableLiveData()
}

