package com.stepasha.keyconservation.model

import android.location.Location
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "user")
data class User(
    var userid: Long? = null,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "user_roomId")
    var userRoomId: Int? = null,
    var profilepicture: String? = null,
    var username: String? = null,
    var password: String? = null,
    var primaryemail: String? = null,
    var firstname: String? = null,
    var lastname: String? = null,
    var position: Boolean? = null,
    var mini_bio: String? = null,
    var species: String? = null,
    var facebook: String? = null,
    var instagram: String? = null,
    var twitter: String? = null,
    var location: String? = null,
    var latitude: Double? = null,
    var longitute: Double? = null,
    var about_us: String? = null,
    var issues: String? = null




):Serializable
class UserLocation{
    var userid: Long? = null

    var profilepicture: String? = null
    var username: String? = null
    var password: String? = null
    var primaryemail: String? = null
    var firstname: String? = null
    var lastname: String? = null
    var position: Boolean? = null
    var mini_bio: String? = null
    var species: String? = null
    var facebook: String? = null
    var instagram: String? = null
    var twitter: String? = null
    var location: String? = null
    var locating: Location? = null
    var latitude: Double? = null
    var longitute: Double? = null
    var about_us: String? = null
    var issues: String? = null

    constructor(
        userid: Long?,

        profilepicture: String?,
        username: String?,
        password: String?,
        primaryemail: String?,
        firstname: String?,
        lastname: String?,
        position: Boolean?,
        mini_bio: String?,
        species: String?,
        facebook: String?,
        instagram: String?,
        twitter: String?,
        location: String?,
        locating: Location?,
        latitude: Double?,
        longitute: Double?,
        about_us: String?,
        issues: String?
    ) {
        this.userid = userid

        this.profilepicture = profilepicture
        this.username = username
        this.password = password
        this.primaryemail = primaryemail
        this.firstname = firstname
        this.lastname = lastname
        this.position = position
        this.mini_bio = mini_bio
        this.species = species
        this.facebook = facebook
        this.instagram = instagram
        this.twitter = twitter
        this.location = location
        this.locating = locating
        this.latitude = latitude
        this.longitute = longitute
        this.about_us = about_us
        this.issues = issues
    }




}






data class Result(val username: String? = null, val position: Boolean? = null, val userid: Long? = null)
data class UserResult(val result: Result, val position: Boolean? = null, val  userid: Long? = null)