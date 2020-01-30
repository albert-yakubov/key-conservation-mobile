package com.stepasha.keyconservation.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


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
    var about_us: String? = null,
    var issues: String? = null



)
data class Result(val username: String? = null, val position: Boolean? = null, val userid: Long? = null)
data class UserResult(val result: Result, val position: Boolean? = null, val  userid: Long? = null)