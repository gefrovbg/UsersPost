package com.example.data.repository.users

import com.example.link.models.user.UserModel

interface DatabaseInputAllUsersRepository {

    suspend fun execute(users: ArrayList<UserModel>): Boolean

}