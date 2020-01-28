package com.stepasha.keyconservation.room

import android.content.Context
import androidx.lifecycle.LiveData
import com.stepasha.keyconservation.model.User
import com.stepasha.keyconservation.model.UserLogin
import com.stepasha.keyconservation.retrofit.LoginServiceSql

class UserDBRepository(val context: Context) : UserRepoInterface {
    override fun loginUser(user: UserLogin) {
    }

    private val database = AppDB.getDatabase(context)
    private val apiFactory = apiFactory()


    override fun apiFactory(): LoginServiceSql {
        return super.apiFactory()
    }

    // Room
    override fun getUserData(id: Int): LiveData<User> {
        return database.userDao().getUserData(id)
    }

    override fun create(obj: User) {
        database.userDao().insert(obj)
    }

    override fun update(obj: User) {
        database.userDao().update(obj)
    }

    override fun delete(obj: User) {
        database.userDao().delete(obj)
    }

    override fun nukeUserTable() {
        database.userDao().nukeUserTable()
    }

    override fun deleteOldUsers() {
        database.userDao().deleteOldUsers()
    }
}