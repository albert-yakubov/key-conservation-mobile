package com.stepasha.keyconservation.retrofit

import com.stepasha.keyconservation.model.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LoginServiceSql{


    @GET("campaigns/campaigns")
    //fun getAllProperties(@Header("Authorization") authToken: String): Call<Properties>
    fun getAllCampaigns() : Call<MutableList<Campaign>>



    @POST("createnewuser")
    fun createUser(@Body newUser: NewUser): Call<RegisterResponse>

    @POST("campaigns/campaign")
    //  fun createProperty(@Header("Authorization") authToken: String, @Body newProperty: NewProperty): Call<Void>
    fun createNewCampaign(@Body newCampaign: NewCampaign) : Call<Void>

    @GET("users/user/name/{userName}")

    fun getUser(@Path("userName")username: String): Call<UserResult>

    companion object {

        const val BASE_URL = "https://key-conservation-mobile.herokuapp.com/"

        private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .baseUrl(BASE_URL)
            .build()
    }

}