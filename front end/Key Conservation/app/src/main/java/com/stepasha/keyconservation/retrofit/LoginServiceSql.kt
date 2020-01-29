package com.stepasha.keyconservation.retrofit

import com.stepasha.keyconservation.model.Campaign
import com.stepasha.keyconservation.model.NewUser
import com.stepasha.keyconservation.model.RegisterResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginServiceSql{


    @GET("campaigns/campaigns")
    //fun getAllProperties(@Header("Authorization") authToken: String): Call<Properties>
    fun getAllCampaigns() : Call<MutableList<Campaign>>



    @POST("createnewuser")
    fun createUser(@Body newUser: NewUser): Call<RegisterResponse>

    companion object {

        const val BASE_URL = "https://key-conservation-mobile.herokuapp.com/"

        private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .baseUrl(BASE_URL)
            .build()
    }

}