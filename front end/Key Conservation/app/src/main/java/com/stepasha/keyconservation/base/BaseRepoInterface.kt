package com.stepasha.keyconservation.base

import com.stepasha.keyconservation.retrofit.LoginServiceSql
import com.stepasha.keyconservation.retrofit.ServiceBuilder

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