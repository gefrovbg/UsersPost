package com.example.data.repository.posts.impl

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.data.repository.posts.DatabaseInputAllPostsRepository
import com.example.link.models.post.Post

class DatabaseInputAllPostsRepositoryImpl(context: Context, factory: SQLiteDatabase.CursorFactory?): DatabaseInputAllPostsRepository {

    private val sqLiteRepository = SQLitePostsRepositoryImpl(context = context, factory = factory)

    override suspend fun execute(posts: ArrayList<Post>): Boolean {

        return sqLiteRepository.postAllUsersPosts(posts)

    }
}