package com.stepasha.endangeredhaven.base

import com.stepasha.endangeredhaven.retrofit.LoginServiceSql
import com.stepasha.endangeredhaven.retrofit.ServiceBuilder

interface BaseRepoInterface<T> {

    fun apiFactory(): LoginServiceSql {
        val receiptTrackerFactory by lazy {
            ServiceBuilder.create()
        }
        return receiptTrackerFactory
    }

    fun create(obj: T)

    fun update(obj: T)

    fun delete(obj: T)
}