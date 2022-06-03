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
import com.example.link.models.user.UserModel
import com.example.userspost.R
import com.example.userspost.databinding.UserItemBinding

class UsersListAdapter(private val onItemClicked: (user: UserModel, textView: TextView, card: CardView, emailTextView: TextView) -> Unit): RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {

    private val usersList = arrayListOf<UserModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setUsers(users: ArrayList<UserModel>){
        this.usersList.clear()
        this.usersList.addAll(users)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListAdapter.ViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersListAdapter.ViewHolder, position: Int) {
        if (usersList != null) {
            val currentUser = usersList[position]

            with(holder) {
                binding.email.transitionName = "email$position"
                binding.card.transitionName = "card$position"
                binding.name.transitionName = "name$position"
                binding.name.text = "${currentUser.name}"
                binding.username.text = "${currentUser.username}"
                binding.email.text = "${currentUser.email}"

//                binding.card.setOnClickListener {
//                    userClickListener.onChatListItemClick(binding.ivImage, currentUser)
//                }
                holder.itemView.setOnClickListener(View.OnClickListener {
                    onItemClicked(currentUser, binding.name, binding.card, binding.email)
                })
//                binding.root.setOnClickListener { onItemClicked(currentUser, binding.name) }
            }
        }
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

}