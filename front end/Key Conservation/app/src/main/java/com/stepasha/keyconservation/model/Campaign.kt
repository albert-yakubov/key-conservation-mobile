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

data class NewCampaign(
    var title: String,

    var banner_image: String,

    var location: String,

    var created_at: Long,

    var event_image: String,

    var event_name: String,

    var event_description: String


) : Serializable