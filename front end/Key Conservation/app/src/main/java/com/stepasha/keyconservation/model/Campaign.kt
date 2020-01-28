package com.stepasha.keyconservation.model

import java.io.Serializable
import java.util.*

data class Campaign(

    val banner_image: String? = null,

    val location: String? = null,

    val created_at: Long? = null,

    val event_image: String? = null,

    val event_name: String? = null,

    val event_description: String? = null


) : Serializable