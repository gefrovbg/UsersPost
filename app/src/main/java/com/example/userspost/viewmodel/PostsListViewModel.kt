package com.example.userspost.viewmodel

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.example.data.repository.posts.DatabaseInputAllPostsRepository
import com.example.data.repository.posts.GetAllCachedPostsOfUserRepository
import com.example.link.models.post.Post
import com.example.link.models.user.UserModel
import com.example.link.repository.GetAllPostsRepository
import com.example.userspost.presentation.MainActivity
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class PostsListViewModel @AssistedInject constructor (
    private val getAllPostsRepository: GetAllPostsRepository,
    private val addAllPostsToLocalDB: DatabaseInputAllPostsRepository,
    private val getAllCachedPostsOfUserRepository: GetAllCachedPostsOfUserRepository,
    @Assisted private val handle: SavedStateHandle,
    @Assisted private val user: UserModel
): ViewModel() {


    @AssistedFactory
    interface PostsListViewModelFactory {
        fun create(
            handle: SavedStateHandle,
            user: UserModel
        ): PostsListViewModel
    }

    val postsList = MutableLiveData<ArrayList<Post>>()

    init {

        viewModelScope.launch {

            if (MainActivity.showConnectionBoolean.value == true){
                val responsePosts = getAllPostsRepository.execute()
                val responsePostsOfUser = arrayListOf<Post>()
                if (responsePosts != null){
                    for (i in responsePosts.indices){
                        if (responsePosts[i].userId == user.id){
                            responsePostsOfUser.add(responsePosts[i])
                        }
                    }
                    postsList.postValue(responsePostsOfUser)
                }
                addAllPostsToLocalDB.execute(responsePosts!!)
            }else{
                val cachedPosts = getAllCachedPostsOfUserRepository.execute(user)
                postsList.postValue(cachedPosts)
            }

        }

        MainActivity.showConnectionBoolean.observeForever(){
            if (it){
                viewModelScope.launch {
                    val responsePosts = getAllPostsRepository.execute()
                    val responsePostsOfUser = arrayListOf<Post>()
                    if (responsePosts != null){
                        for (i in responsePosts.indices){
                            if (responsePosts[i].userId == user.id){
                                responsePostsOfUser.add(responsePosts[i])
                            }
                        }
                        postsList.postValue(responsePostsOfUser)
                    }
                    addAllPostsToLocalDB.execute(responsePosts!!)
                }
            }
        }

    }

    companion object{
        fun provideFactory(
            assistedFactory: PostsListViewModelFactory,
            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle? = null,
            user: UserModel
        ): AbstractSavedStateViewModelFactory = object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
                return assistedFactory.create(
                    handle,
                    user
                ) as T
            }
        }
    }


}