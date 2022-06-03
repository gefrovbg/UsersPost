package com.example.data.repository.users

import com.example.link.models.user.UserModel

interface GetAllCachedUsersRepository {

    suspend fun execute(): ArrayList<UserModel>

}