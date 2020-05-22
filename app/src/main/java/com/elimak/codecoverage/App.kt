package com.elimak.codecoverage

import android.app.Application
import com.elimak.codecoverage.di.ApplicationComponent
import com.elimak.codecoverage.di.ApplicationModule
import com.elimak.codecoverage.di.DaggerApplicationComponent

open class App: Application() {
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
