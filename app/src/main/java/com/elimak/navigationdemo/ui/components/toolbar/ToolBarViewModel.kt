package com.elimak.navigationdemo.ui.components.toolbar

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.elimak.navigationdemo.repository.IAppStateRepository
import com.elimak.navigationdemo.repository.data.AppState
import com.elimak.navigationdemo.repository.data.ToolBarActionEvent
import com.elimak.navigationdemo.repository.data.ToolBarState
import kotlinx.coroutines.launch
import javax.inject.Inject

class ToolBarViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * - make the toolBarState available for the layout databindings
     * - collect the users clicks on toolbar buttons and
     * trigger the broadcast of the event using the repository
     *
     * This will allow the NavigableFragment currently active to fire the Navigation.navigate() method
     */

    @Inject lateinit var appStateRepository: IAppStateRepository

    val toolBarState = MutableLiveData<ToolBarState>()
    val backgroundVisible = MutableLiveData<Boolean>()

    val onAppStateChanged = Observer<AppState> {
        toolBarState.value = it.toolBarState
        backgroundVisible.value = it.toolBarBackgroundVisible
    }

    fun postInjectionInit() {
        appStateRepository.getAppState.observeForever(onAppStateChanged)
    }

    override fun onCleared() {
        appStateRepository.getAppState.removeObserver(onAppStateChanged)
        super.onCleared()
    }

    fun onRightIconClicked() {
        viewModelScope.launch {
            when(toolBarState.value?.rightIconRes) {
                ToolBarState.OPTIONS_RES_ID -> {
                    appStateRepository.setToolBarAction(ToolBarActionEvent.Actions.OPTIONS_CLICKED)
                }

                ToolBarState.LOGO_RES_ID -> {
                    appStateRepository.setToolBarAction(ToolBarActionEvent.Actions.SETTINGS_CLICKED)
                }
            }
        }
    }

    fun onLeftIconClicked() {
        viewModelScope.launch {
            when (toolBarState.value?.leftIconRes) {
                ToolBarState.OPTIONS_RES_ID -> {
                    appStateRepository.setToolBarAction(ToolBarActionEvent.Actions.OPTIONS_CLICKED)
                }
                ToolBarState.LOGO_RES_ID -> {
                    appStateRepository.setToolBarAction(ToolBarActionEvent.Actions.SETTINGS_CLICKED)
                }
            }
        }
    }
}
