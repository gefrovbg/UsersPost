package com.example.data.repository.users.impl

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.data.repository.users.DatabaseInputAllUsersRepository
import com.example.link.models.user.UserModel

class DatabaseInputAllUsersRepositoryImpl(context: Context, factory: SQLiteDatabase.CursorFactory?):
    DatabaseInputAllUsersRepository {

    private val sqLiteRepository = SQLiteUsersRepositoryImpl(context = context, factory = factory)

    override suspend fun execute(users: ArrayList<UserModel>): Boolean {

        return sqLiteRepository.postAllUsers(users)

    }

}