package com.elimak.codecoverage.ui.landing

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.elimak.codecoverage.ui.base.AndroidViewModelBase

class LandingViewModel(application: Application) : AndroidViewModelBase(application) {
    val text: MutableLiveData<String> = MutableLiveData("Landing Page")
    val secondText: MutableLiveData<String> = MutableLiveData("secondText")
}
