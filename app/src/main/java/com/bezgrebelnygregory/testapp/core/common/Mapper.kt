package com.bezgrebelnygregory.testapp.core.common

interface Mapper<from, to> {
    fun map(from: from): to
}