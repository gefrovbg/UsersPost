package com.example.link.repository.impl

import com.example.link.models.post.Post
import com.example.link.objects.RetrofitHelper
import com.example.link.repository.GetAllPostsRepository
import com.example.link.repository.JSONPlaceholder

class GetAllPostsRepositoryImpl: GetAllPostsRepository {

    private val jsonPlaceholderApi = RetrofitHelper.getInstance().create(JSONPlaceholder::class.java)

    override suspend fun execute(): ArrayList<Post>? {

        return jsonPlaceholderApi.getAllPosts().body()

    }

}