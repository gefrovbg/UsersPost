package com.example.userspost.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.example.link.models.post.Post
import com.example.link.models.user.UserModel
import com.example.userspost.R
import com.example.userspost.databinding.PostItemBinding
import com.example.userspost.databinding.UserItemBinding

class PostsListAdapter(): RecyclerView.Adapter<PostsListAdapter.ViewHolder>() {

    private val postList = arrayListOf<Post>()

    @SuppressLint("NotifyDataSetChanged")
    fun setPosts(posts: ArrayList<Post>){
        this.postList.clear()
        this.postList.addAll(posts)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsListAdapter.ViewHolder {
        val binding = PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsListAdapter.ViewHolder, position: Int) {
        if (postList != null) {
            val currentPost = postList[position]

            with(holder) {
                binding.title.text = "${currentPost.title}"
                binding.body.text = "${currentPost.body}"
            }
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }

}