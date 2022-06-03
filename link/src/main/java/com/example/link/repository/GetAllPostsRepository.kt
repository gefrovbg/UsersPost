package com.example.link.repository

import com.example.link.models.post.Post

interface GetAllPostsRepository {

    suspend fun execute(): ArrayList<Post>?

}