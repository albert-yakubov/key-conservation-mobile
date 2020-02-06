package com.stepasha.keyconservation.model

import java.io.Serializable
import java.util.*

data class Campaign(
    val eventid: Long? = null,

    val banner_image: String? = null,

    val location: String? = null,

    val latitude: Double? = null,

    val longitude: Double? = null,

    val created_at: Long? = null,

    val event_image: String? = null,

    val event_name: String? = null,

    val event_description: String? = null


) : Serializable

data class NewCampaign(
    var title: String,

    var banner_image: String,

    var location: String,
    var latitude: Double,

    var longitude: Double,

    var created_at: String,

    var event_image: String,

    var event_name: String,

    var event_description: String


) : Serializable

data class CampResult(val latitude: Double? = null, val longitude: Double? = null)
data class CampResults(val result: CampResult, val latitude: Double? = null, val longitude: Double? = null)