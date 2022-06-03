package com.example.link.repository.impl

import com.example.link.models.user.UserModel
import com.example.link.objects.RetrofitHelper
import com.example.link.repository.GetAllUsersRepository
import com.example.link.repository.JSONPlaceholder

class GetAllUsersRepositoryImpl: GetAllUsersRepository {

    private val jsonPlaceholderApi = RetrofitHelper.getInstance().create(JSONPlaceholder::class.java)

    override suspend fun execute(): ArrayList<UserModel>? {

            return jsonPlaceholderApi.getAllUsers().body()

    }


}