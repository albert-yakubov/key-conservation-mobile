package com.stepasha.keyconservation.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class NewUser (

    @SerializedName("firstname")
    var firstName: String?,
    @SerializedName("lastname")
    var lastName: String?,
    @SerializedName("primaryemail")
    var email: String?,
    @SerializedName("username")
    var username: String?,
    @SerializedName("password")
    var password: String?,
    @SerializedName("position")
    var position: Boolean
) : Serializable

