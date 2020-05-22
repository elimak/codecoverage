package com.elimak.navigationdemo.repository.data


data class ToolBarActionEvent (val type: Actions) {
    enum class Actions {
        OPTIONS_CLICKED,
        SETTINGS_CLICKED
    }
}