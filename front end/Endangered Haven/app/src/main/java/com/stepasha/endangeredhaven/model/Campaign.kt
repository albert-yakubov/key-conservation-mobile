package com.stepasha.endangeredhaven.model

import java.io.Serializable

data class Campaign(
    val eventid: Long? = null,
    val title: String? = null,
    val banner_image: String? = null,

    val location: String? = null,

    val latitude: Double? = null,

    val longitude: Double? = null,

    val created_at: Long? = null,

    val event_image: String? = null,

    val event_name: String? = null,

    val event_description: String? = null,

    val user: CampUser


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

    var event_description: String,
    var user: NewCampUser


) : Serializable

data class CampUser(var userid: Long, var username: String, var  profilepicture: String):Serializable

data class NewCampUser(var userid: Long):Serializable


data class CampResult(val latitude: Double? = null, val longitude: Double? = null)
data class CampResults(val result: CampResult, val latitude: Double? = null, val longitude: Double? = null)