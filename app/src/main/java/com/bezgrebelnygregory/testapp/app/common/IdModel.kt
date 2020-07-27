package com.bezgrebelnygregory.testapp.app.common

interface IdModel {
    val id: Long

    val idNotSet: Boolean
        get() = id == NO_ID

    val idSet: Boolean
        get() = id != NO_ID

    companion object {
        const val NO_ID = 0L
    }
}