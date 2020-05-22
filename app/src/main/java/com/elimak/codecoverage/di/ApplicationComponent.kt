package com.elimak.codecoverage.di

import android.content.Context
import com.elimak.codecoverage.App
import com.elimak.codecoverage.ui.landing.LandingPage
import com.elimak.navigationtabsdemo.MainActivity

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    // APP
    fun inject(application: App)

    // activity
    fun inject(activity: MainActivity)

    // fragment
    fun inject(frag: LandingPage)

    @AppContext
    fun getContext(): Context
}