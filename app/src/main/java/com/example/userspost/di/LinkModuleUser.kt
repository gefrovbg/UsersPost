package com.example.userspost.di

import com.example.link.repository.GetAllPostsRepository
import com.example.link.repository.GetAllUsersRepository
import com.example.link.repository.impl.GetAllPostsRepositoryImpl
import com.example.link.repository.impl.GetAllUsersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.assisted.AssistedInject
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class LinkModuleUser {

    @Provides
    fun provideGetAllUsersRepository(): GetAllUsersRepository {
        return GetAllUsersRepositoryImpl()
    }

}