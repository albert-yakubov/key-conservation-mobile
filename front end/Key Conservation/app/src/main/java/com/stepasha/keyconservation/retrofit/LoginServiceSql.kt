package com.stepasha.keyconservation.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface LoginServiceSql{



    companion object {

        const val BASE_URL = "https://ay-mylocator.herokuapp.com/"

        private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .baseUrl(BASE_URL)
            .build()
    }

}