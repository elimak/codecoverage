package com.elimak.codecoverage.di

import android.app.Application
import android.content.Context
import com.elimak.codecoverage.App

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: App) {

    @Provides
    @AppContext
    fun provideContext(): Context = app.applicationContext

    @Provides
    @AppContext
    fun provideApplication(): Application = app

}