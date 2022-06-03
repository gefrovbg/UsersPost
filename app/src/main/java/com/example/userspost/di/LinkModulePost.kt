package com.example.userspost.di

import com.example.link.repository.GetAllPostsRepository
import com.example.link.repository.impl.GetAllPostsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
class LinkModulePost {

    @Provides
    fun provideGetAllPostsRepository(): GetAllPostsRepository {
        return GetAllPostsRepositoryImpl()
    }

}