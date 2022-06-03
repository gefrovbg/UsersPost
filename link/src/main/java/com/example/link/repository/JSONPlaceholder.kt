package com.example.link.repository

import com.example.link.models.post.Post
import com.example.link.models.user.UserModel
import retrofit2.Response
import retrofit2.http.GET

interface JSONPlaceholder {

    @GET("/users")
    suspend fun getAllUsers() : Response<ArrayList<UserModel>>

    @GET("/posts")
    suspend fun getAllPosts() : Response<ArrayList<Post>>

}