package com.stepasha.keyconservation.room

import androidx.lifecycle.LiveData
import com.stepasha.keyconservation.base.BaseRepoInterface
import com.stepasha.keyconservation.model.User
import com.stepasha.keyconservation.model.UserLogin

interface UserRepoInterface : BaseRepoInterface<User> {

    fun getUserData(id: Int): LiveData<User>

    fun loginUser(user: UserLogin)

    fun nukeUserTable()

    fun deleteOldUsers()
}