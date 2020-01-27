package com.stepasha.keyconservation.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T> : ViewModel() {

    abstract fun create(obj: T)

    abstract fun update(obj: T)

    abstract fun delete(obj: T)

}