package com.stepasha.keyconservation

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest=Config.NONE)
class RobolectricIntegrationTest {
    lateinit var activity: MainActivity

    @Before
    @Throws(Exception::class)
    fun setUp() {
        activity = Robolectric.buildActivity<MainActivity>(MainActivity::class.java)
            .create()
            .resume()
            .get()
    }
    @Test

    fun creatingPost_ShouldNOT_be_Null() {
        // Given
        val postActivity = SplashActivity()
        // When
        // Then
        assertNotNull(postActivity)
    }
    @Test
  //  @Throws(java.lang.Exception::class)
    fun shouldNotBeNull() {
        assertNotNull(activity)
    }




}