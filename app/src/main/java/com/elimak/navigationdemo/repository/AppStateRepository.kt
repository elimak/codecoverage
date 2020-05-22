package com.elimak.navigationdemo.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elimak.navigationdemo.repository.data.AppState
import com.elimak.navigationdemo.repository.data.ToolBarActionEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ReceiveChannel
import javax.inject.Inject

@ExperimentalCoroutinesApi
class AppStateRepository @Inject constructor(val context: Context): IAppStateRepository {

    /**
     * Basic Repository to hold the AppState and broadcast the toolbar actions
     */

    private val appStateLive = MutableLiveData<AppState>(AppState.Builder().navigationBar(false).toolBar(false).build())
    override val getAppState: LiveData<AppState>
        get() = appStateLive

    val publisher = BroadcastChannel<ToolBarActionEvent>(1)
    override val toolBarActionChannel: ReceiveChannel<ToolBarActionEvent>
        get() = publisher.openSubscription()

    override fun setState(state: AppState) {
        appStateLive.postValue(state)
    }

    override suspend fun setToolBarAction(actionEvent: ToolBarActionEvent.Actions) {
        publisher.send(ToolBarActionEvent(actionEvent))
    }
}