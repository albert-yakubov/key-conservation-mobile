package com.stepasha.endangeredhaven.viewmodel

import androidx.lifecycle.ViewModel
import com.stepasha.endangeredhaven.model.User
import com.stepasha.endangeredhaven.service.FollowService
import javax.inject.Inject

class FollowViewModel @Inject constructor(): ViewModel() {

    @Inject
    lateinit var followService: FollowService

    fun followUser(user: User) = followService.api.followUser(mapOf("followUsername" to user.username))

    fun unfollowUser(user: User) = followService.api.unfollowUser(mapOf("unfollowUsername" to user.username))

}