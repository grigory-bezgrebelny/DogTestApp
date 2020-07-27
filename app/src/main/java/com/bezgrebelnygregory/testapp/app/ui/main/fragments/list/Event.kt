package com.bezgrebelnygregory.testapp.app.ui.main.fragments.list

sealed class Event {
    data class Loading(val value: Boolean) : Event()
    data class Error(val desc: String) : Event()
}