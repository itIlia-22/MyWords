package com.example.mywords.view.base

import com.example.mywords.model.data.AppState

// level 0 View context and framework
interface View {
    fun renderData(appState: AppState)
}
