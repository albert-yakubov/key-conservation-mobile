package com.stepasha.keyconservation.retrofit

import com.stepasha.keyconservation.model.*
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface LoginServiceSql{


    @GET("campaigns/campaigns")
    //fun getAllProperties(@Header("Authorization") authToken: String): Call<Properties>
    fun getAllCampaigns() : Call<MutableList<Campaign>>



    @GET("users/users")
    //fun getAllUsers(@Header("Authorization") authToken: String): Call<Properties>
    fun getAllUsers() : Call<MutableList<User>>

    @GET("users/user/{userId}")
    fun getUserById(@Path("userId") userId: Long) : Call<User>

    @PUT("users/user/{id}")
    fun updateUserById(@Path("id") id: Long, @Body updateUser: UpdateUser) : Call<Void>



    @POST("users/user")
    fun createUser(@Body newUser: Neweruser): Call<RegisterResponse>

    @POST("campaigns/campaign")
    //  fun createProperty(@Header("Authorization") authToken: String, @Body newProperty: NewProperty): Call<Void>
    fun createNewCampaign(@Body newCampaign: NewCampaign) : Call<Void>

    @GET("users/user/name/{userName}")

    fun getUser(@Path("userName")username: String): Call<UserResult>

    @DELETE("campaigns/campaign/{eventid}")
    fun deleteCampaign(@Path("eventid") eventid: Long) : Call<Void>

    @GET("users/user/name/{userName}")
    fun getUser2(@Path("userName")username: String): Call<User>



    @GET("campaigns/campaign/title/like/{title}")
    fun getFoundUser(@Path("title") title: String): Observable<MutableList<Campaign>>
    @GET("campaigns/campaign/event_name/like/{eventname}")
    fun getEventName(@Path("eventname") eventname: String): Observable<MutableList<Campaign>>
    companion object {

        const val BASE_URL = "https://key-conservation-mobile.herokuapp.com/"

        private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .baseUrl(BASE_URL)
            .build()


    }

}