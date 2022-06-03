package com.example.data.repository.posts

import com.example.link.models.post.Post

interface DatabaseInputAllPostsRepository {

    suspend fun execute(posts: ArrayList<Post>): Boolean

}