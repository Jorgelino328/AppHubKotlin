package com.caio.caiocardozo.partidabasquete_kotlin.ui.scoreboard.renderer

import android.view.View
import android.view.ViewGroup

interface ScoreRenderer<T> {
    fun createView(container: ViewGroup): View
    fun bind(state: T)
}