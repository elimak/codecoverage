package com.elimak.navigationdemo.di

import android.app.Application
import android.content.Context
import com.elimak.navigationdemo.App
import com.elimak.navigationdemo.repository.AppStateRepository
import com.elimak.navigationdemo.repository.IAppStateRepository

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

    @Provides
    @Singleton
    fun providesAppStateRepository(@AppContext context: Application): IAppStateRepository {
        return AppStateRepository(context)
    }
}