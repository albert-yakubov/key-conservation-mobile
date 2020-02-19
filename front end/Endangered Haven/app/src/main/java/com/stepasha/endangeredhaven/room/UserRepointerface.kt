package com.stepasha.endangeredhaven.room

import androidx.lifecycle.LiveData
import com.stepasha.endangeredhaven.base.BaseRepoInterface
import com.stepasha.endangeredhaven.model.User
import com.stepasha.endangeredhaven.model.UserLogin

interface UserRepoInterface : BaseRepoInterface<User> {

    fun getUserData(id: Int): LiveData<User>

    fun loginUser(user: UserLogin)

    fun nukeUserTable()

    fun deleteOldUsers()
}