package com.n1n3b1t.ghusers.users

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.n1n3b1t.ghusers.databinding.ItemUserBinding
import com.n1n3b1t.ghusers.entity.User

/**
 * Created by valentintaranenko on 02/01/2018.
 */
class UserListAdapter : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    var items = listOf<User>()


    fun updateUsers(users: List<User>) {
        val calculateDiff = DiffUtil.calculateDiff(UserDiffCallback(items, users))
        items = users
        calculateDiff.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[holder.adapterPosition])
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }


    class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.user = user
            binding.executePendingBindings()
        }
    }

    class UserDiffCallback(val oldList: List<User>, val newList: List<User>) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = newList[newItemPosition].id == oldList[oldItemPosition].id

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = newList[newItemPosition] == oldList[oldItemPosition]

    }
}