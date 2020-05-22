package com.elimak.navigationdemo

import android.app.Application
import com.elimak.navigationdemo.di.ApplicationComponent
import com.elimak.navigationdemo.di.ApplicationModule
import com.elimak.navigationdemo.di.DaggerApplicationComponent

class App: Application() {
    private lateinit var instance: App

    override fun onCreate() {
        super.onCreate()
        instance = this
        setup()
    }

    fun setup() {
        injector = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
        injector.inject(this)
    }

    companion object {
        lateinit var injector: ApplicationComponent private set
    }
}
