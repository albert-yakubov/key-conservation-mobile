package com.stepasha.keyconservation.model

import java.io.Serializable
import java.util.*

data class Campaign(
    var id: Int,

    val banner_image: String? = null,

    val location: String? = null,

    val created_at: Date? = null,

    val event_image: String? = null,

    val event_name: String? = null,

    val event_description: String? = null
) : Serializable