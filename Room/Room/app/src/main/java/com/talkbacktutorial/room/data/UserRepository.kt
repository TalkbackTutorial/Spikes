package com.talkbacktutorial.room.data

import androidx.lifecycle.LiveData

/*
A repository class abstracts access to multiple data sources.
This is not compulsory but is recommended practice.
 */
class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}