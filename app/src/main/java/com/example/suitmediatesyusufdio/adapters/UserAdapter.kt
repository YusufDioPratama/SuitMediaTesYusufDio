package com.example.suitmediatesyusufdio.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suitmediatesyusufdio.models.User
import com.example.suitmediatesyusufdio.R


class UserAdapter(
    private val list: List<User>,
    private val onItemClick: (User) -> Unit
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvEmail: TextView = itemView.findViewById(R.id.tvEmail)
        private val ivAvatar: ImageView = itemView.findViewById(R.id.ivAvatar)

        fun bind(user: User) {
            tvName.text = "${user.first_name} ${user.last_name}"
            tvEmail.text = user.email
            Glide.with(ivAvatar).load(user.avatar).circleCrop().into(ivAvatar)
            itemView.setOnClickListener { onItemClick(user) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[position])
}
