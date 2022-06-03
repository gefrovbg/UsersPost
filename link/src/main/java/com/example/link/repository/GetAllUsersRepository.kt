package com.example.link.repository

import com.example.link.models.user.UserModel


interface GetAllUsersRepository {

    suspend fun execute(): ArrayList<UserModel>?

}