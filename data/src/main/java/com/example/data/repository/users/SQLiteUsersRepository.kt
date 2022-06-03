package com.example.data.repository.users

import com.example.link.models.user.UserModel

interface SQLiteUsersRepository {

    suspend fun postAllUsers(users: ArrayList<UserModel>): Boolean

    suspend fun getAllUsers(): ArrayList<UserModel>

    suspend fun getUserByUsername(searchUserName: String): UserModel?

}