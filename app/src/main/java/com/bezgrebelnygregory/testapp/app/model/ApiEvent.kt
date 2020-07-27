package com.bezgrebelnygregory.testapp.app.model

import com.bezgrebelnygregory.testapp.core.model.ApiModel

data class ApiEvent<T>(val event: ApiModel<T>)