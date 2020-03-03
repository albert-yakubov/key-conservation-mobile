package com.stepasha.endangeredhaven.model

import android.os.Parcel
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
    var ulatitude: Double? = null,
    var ulongitude: Double? = null,
    var about_us: String? = null,
    var issues: String? = null
  //  @SerializedName("following")
  //  var followingUsers: MutableList<User> = mutableListOf(),
  //  @SerializedName("followers")
  //  var followerUsers: MutableList<User> = mutableListOf()






):Serializable


class UpdateUser(

    var mini_bio: String? = null,
    var species: String? = null,
    var facebook: String? = null,
    var instagram: String? = null,
    var twitter: String? = null,
    var location: String? = null,
    var ulatitude: Double? = null,
    var ulongitude: Double? = null,
    var about_us: String? = null,
    var issues: String? = null


)

class ResetPassword(

    var mini_bio: String? = null,
    var species: String? = null,
    var facebook: String? = null,
    var instagram: String? = null,
    var twitter: String? = null,
    var location: String? = null,
    var ulatitude: Double? = null,
    var ulongitude: Double? = null,
    var about_us: String? = null,
    var issues: String? = null,
    var password: String? = null


)





data class Result(val username: String? = null, val position: Boolean? = null, val userid: Long? = null, val ulatitude: Double? = null, val ulongitude: Double? = null)
data class UserResult(val username: String? = null, val result: Result, val position: Boolean? = null, val  userid: Long? = null,  val ulatitude: Double? = null, val ulongitude: Double? = null)