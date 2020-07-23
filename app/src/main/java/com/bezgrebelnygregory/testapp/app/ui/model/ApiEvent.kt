package com.bezgrebelnygregory.testapp.app.ui.model

import com.bezgrebelnygregory.testapp.core.model.ApiModel

data class ApiEvent<T>(val event: ApiModel<T>)