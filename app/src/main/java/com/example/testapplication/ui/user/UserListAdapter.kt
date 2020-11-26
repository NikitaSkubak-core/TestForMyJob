package com.example.testapplication.ui.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.api.PlaceHolderUser
import com.example.testapplication.databinding.ItemUserBinding

class UserListAdapter: RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {
    var usersList: List<PlaceHolderUser> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder.from(parent)

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = usersList[position]
        holder.bindUser(currentUser)
    }

    override fun getItemCount(): Int = usersList.size

    fun setUsers(users: List<PlaceHolderUser>) {
        usersList = users
        notifyDataSetChanged()
    }

    class UserViewHolder private constructor(var binding: ItemUserBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bindUser(user: PlaceHolderUser) {
            binding.user = user
        }

        companion object {
            fun from(parent: ViewGroup): UserViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemUserBinding.inflate(layoutInflater, parent, false)
                return UserViewHolder(binding)
            }
        }
    }
}