package com.elimak.navigationdemo.repository.data

import com.elimak.navigationdemo.R

data class ToolBarState(
    var title: String = "",
    var leftIconRes: Int? = null,
    var rightIconRes: Int? = null) {

    companion object {
        val LOGO_RES_ID: Int = R.drawable.ic_main_small_logo
        val OPTIONS_RES_ID: Int = R.drawable.ic_more_dots
    }

    fun hasTitle() : Boolean {
        return title.isNotEmpty()
    }

    class Builder {
        private var title: String = ""
        private var leftIconRes: Int? = null
        private var rightIconRes: Int? = null

        fun title(title: String) = apply { this.title = title }
        fun rightIconResId(rightButtonResId: Int) = apply { this.rightIconRes = rightButtonResId }
        fun leftIconResId(leftButtonResId: Int) = apply { this.leftIconRes = leftButtonResId }

        fun build() = ToolBarState(
            title,
            leftIconRes,
            rightIconRes
        )
    }
}