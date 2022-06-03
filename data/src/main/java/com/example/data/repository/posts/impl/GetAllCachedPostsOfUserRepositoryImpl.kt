package com.example.data.repository.posts.impl

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.data.repository.posts.GetAllCachedPostsOfUserRepository
import com.example.link.models.post.Post
import com.example.link.models.user.UserModel

class GetAllCachedPostsOfUserRepositoryImpl(context: Context, factory: SQLiteDatabase.CursorFactory?): GetAllCachedPostsOfUserRepository {

    private val sqLiteRepository = SQLitePostsRepositoryImpl(context = context, factory = factory)

    override suspend fun execute(userModel: UserModel): ArrayList<Post> {

        return sqLiteRepository.getAllPostsOfUser(userModel = userModel)

    }

}