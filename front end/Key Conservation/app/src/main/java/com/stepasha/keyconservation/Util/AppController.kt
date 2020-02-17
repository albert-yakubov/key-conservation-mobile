package com.stepasha.keyconservation.Util

import android.app.Application
import com.cloudinary.android.MediaManager

public class AppController : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Cloudinary
        MediaManager.init(this)
    }
}