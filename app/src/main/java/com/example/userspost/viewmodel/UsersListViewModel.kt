package com.example.userspost.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.users.DatabaseInputAllUsersRepository
import com.example.data.repository.users.GetAllCachedUsersRepository
import com.example.link.models.post.Post
import com.example.link.models.user.UserModel
import com.example.link.repository.GetAllPostsRepository
import com.example.link.repository.GetAllUsersRepository
import com.example.userspost.presentation.MainActivity.Companion.showConnectionBoolean
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersListViewModel @Inject constructor (
    private val getAllUsers: GetAllUsersRepository,
    private val addAllUsersToLocalDB: DatabaseInputAllUsersRepository,
    private val getAllCachedUsersRepository: GetAllCachedUsersRepository
): ViewModel() {

    val usersList = MutableLiveData<ArrayList<UserModel>>()

    init {
        viewModelScope.launch {

            if (showConnectionBoolean.value == true){
                val responseUsers = getAllUsers.execute()
                if (responseUsers != null){
                    usersList.postValue(responseUsers)
                }
                addAllUsersToLocalDB.execute(responseUsers!!)
            }else{
                val cachedUsers = getAllCachedUsersRepository.execute()
                usersList.postValue(cachedUsers)
            }

        }

        showConnectionBoolean.observeForever{
            if (it){
                viewModelScope.launch {
                    val responseUsers = getAllUsers.execute()
                    if (responseUsers != null){
                        usersList.postValue(responseUsers)
                    }
                    addAllUsersToLocalDB.execute(responseUsers!!)
                }
            }

        }

    }

}