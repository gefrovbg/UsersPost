package com.example.userspost.di

import android.content.Context
import com.example.data.repository.posts.DatabaseInputAllPostsRepository
import com.example.data.repository.posts.GetAllCachedPostsOfUserRepository
import com.example.data.repository.posts.impl.DatabaseInputAllPostsRepositoryImpl
import com.example.data.repository.posts.impl.GetAllCachedPostsOfUserRepositoryImpl
import com.example.data.repository.users.DatabaseInputAllUsersRepository
import com.example.data.repository.users.GetAllCachedUsersRepository
import com.example.data.repository.users.impl.DatabaseInputAllUsersRepositoryImpl
import com.example.data.repository.users.impl.GetAllCachedUsersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDatabaseInputAllUsersRepository(@ApplicationContext context: Context): DatabaseInputAllUsersRepository{
        return DatabaseInputAllUsersRepositoryImpl(context, null)
    }

    @Provides
    @Singleton
    fun provideGetAllCachedUsersRepository(@ApplicationContext context: Context): GetAllCachedUsersRepository{
        return GetAllCachedUsersRepositoryImpl(context, null)
    }

    @Provides
    @Singleton
    fun provideDatabaseInputAllPostsRepository(@ApplicationContext context: Context): DatabaseInputAllPostsRepository {
        return DatabaseInputAllPostsRepositoryImpl(context, null)
    }

    @Provides
    @Singleton
    fun provideGetAllCachedPostsOfUserRepository(@ApplicationContext context: Context): GetAllCachedPostsOfUserRepository {
        return GetAllCachedPostsOfUserRepositoryImpl(context, null)
    }

}