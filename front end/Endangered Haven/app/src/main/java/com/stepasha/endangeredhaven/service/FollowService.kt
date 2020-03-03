package com.stepasha.endangeredhaven.service

import com.stepasha.endangeredhaven.model.ServerResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.HTTP
import retrofit2.http.POST
import javax.inject.Inject


class FollowService @Inject constructor(retrofit: Retrofit) {

    val api: FollowAPI
    init {
        api = retrofit.create(FollowAPI::class.java)
    }

    interface FollowAPI {
        @POST("/follow")
        fun followUser(@Body followJSON: Map<String, String?>): Single<Response<ServerResponse<String>>>

        @HTTP(method = "DELETE", path = "/follow", hasBody = true)
        fun unfollowUser(@Body followJSON: Map<String, String?>): Single<Response<ServerResponse<String>>>
    }
}