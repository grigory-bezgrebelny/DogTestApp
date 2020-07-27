package com.bezgrebelnygregory.testapp.core.common

abstract class NetDataSource<T> : DataSource<T>() {

    abstract fun fetchData()
}