package com.elimak.navigationdemo.repository

import androidx.lifecycle.LiveData
import com.elimak.navigationdemo.repository.data.AppState
import com.elimak.navigationdemo.repository.data.ToolBarActionEvent
import kotlinx.coroutines.channels.ReceiveChannel

interface IAppStateRepository {
    val getAppState: LiveData<AppState>
    fun setState(state: AppState)

    suspend fun setToolBarAction(actionEvent: ToolBarActionEvent.Actions)
    val toolBarActionChannel: ReceiveChannel<ToolBarActionEvent>
}