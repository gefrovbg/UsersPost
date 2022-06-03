package com.example.data.repository.posts

import com.example.link.models.post.Post
import com.example.link.models.user.UserModel

interface SQLitePostsRepository {

    suspend fun postAllUsersPosts(posts: ArrayList<Post>): Boolean

    suspend fun getAllPostsOfUser(userModel: UserModel): ArrayList<Post>

}