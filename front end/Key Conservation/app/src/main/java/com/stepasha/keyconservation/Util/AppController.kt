package com.stepasha.keyconservation.Util

import android.app.Application
import com.cloudinary.android.MediaManager
import com.stepasha.keyconservation.dagger.DaggerNetworkComponent

public class AppController : Application() {

    val appComponent by lazy {
        DaggerNetworkComponent
            .builder()
            .build()
    }
    override fun onCreate() {
        super.onCreate()
        // Initialize Cloudinary
        MediaManager.init(this)
    }
}