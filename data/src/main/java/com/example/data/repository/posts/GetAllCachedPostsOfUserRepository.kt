package com.example.data.repository.posts

import com.example.link.models.post.Post
import com.example.link.models.user.UserModel

interface GetAllCachedPostsOfUserRepository {

    suspend fun execute(userModel: UserModel): ArrayList<Post>

}