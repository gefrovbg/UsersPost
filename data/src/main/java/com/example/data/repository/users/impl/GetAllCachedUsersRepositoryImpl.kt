package com.example.data.repository.users.impl

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.data.repository.users.GetAllCachedUsersRepository
import com.example.link.models.user.UserModel

class GetAllCachedUsersRepositoryImpl(context: Context, factory: SQLiteDatabase.CursorFactory?):
    GetAllCachedUsersRepository {

    private val sqLiteRepository = SQLiteUsersRepositoryImpl(context = context, factory = factory)

    override suspend fun execute(): ArrayList<UserModel> {
        return sqLiteRepository.getAllUsers()
    }

}