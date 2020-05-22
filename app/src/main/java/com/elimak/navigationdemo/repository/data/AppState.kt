package com.elimak.navigationdemo.repository.data

data class AppState (
    val toolBarVisible: Boolean,
    val navigationBarVisible: Boolean,
    val toolBarBackgroundVisible: Boolean,
    val navigationBarBackgroundVisible: Boolean,
    val toolBarState: ToolBarState) {

    class Builder {
        private var toolBarVisible: Boolean = true
        private var navigationBarVisible: Boolean = true
        private var toolBarBackground: Boolean = true
        private var navigationBarBackground: Boolean = true
        private var topBarModel: ToolBarState = ToolBarState.Builder().build()

        fun toolBar(visible: Boolean) = apply {
            this.toolBarVisible = visible
            toolBarBackground = visible
        }

        fun navigationBar(visible: Boolean) = apply {
            this.navigationBarVisible = visible
            navigationBarBackground = visible
        }

        fun toolBarBackground(visible: Boolean) = apply {
            this.toolBarBackground = if(!toolBarVisible) false else visible
        }

        fun navigationBarBackground(visible: Boolean) = apply {
            this.navigationBarBackground = if(!navigationBarVisible) false else visible
        }

        fun topBarState(topBarModel: ToolBarState) = apply { this.topBarModel = topBarModel }

        fun build() = AppState(
            toolBarVisible,
            navigationBarVisible,
            toolBarBackground,
            navigationBarBackground,
            topBarModel
        )
    }
}