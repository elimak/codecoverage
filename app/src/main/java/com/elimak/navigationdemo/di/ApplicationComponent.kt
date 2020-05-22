package com.elimak.navigationdemo.di

import android.content.Context
import com.elimak.navigationdemo.App
import com.elimak.navigationdemo.ui.game.GamePage
import com.elimak.navigationdemo.ui.landing.LandingPage
import com.elimak.navigationdemo.ui.settings.SettingsPage
import com.elimak.navigationdemo.ui.tabs.feed.TabFeedPage
import com.elimak.navigationdemo.ui.tabs.notification.TabNotificationPage
import com.elimak.navigationdemo.ui.tabs.profile.TabProfilePage
import com.elimak.navigationdemo.ui.tabs.discovery.TabDiscoveryPage
import com.elimak.navigationdemo.ui.tabs.discovery.nested.TabDiscoveryNestedPage
import com.elimak.navigationdemo.ui.components.toolbar.ToolBarFragment
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
    fun inject(frag: GamePage)
    fun inject(frag: TabFeedPage)
    fun inject(frag: SettingsPage)
    fun inject(frag: TabProfilePage)
    fun inject(frag: TabNotificationPage)
    fun inject(frag: TabDiscoveryNestedPage)
    fun inject(frag: TabDiscoveryPage)
    fun inject(frag: ToolBarFragment)

    @AppContext
    fun getContext(): Context
}