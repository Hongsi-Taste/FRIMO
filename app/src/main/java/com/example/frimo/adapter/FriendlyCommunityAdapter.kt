package com.example.frimo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.frimo.R
import com.example.frimo.data.User

class FriendlyCommunityAdapter(private val context: Context) : RecyclerView.Adapter<FriendlyCommunityAdapter.ViewHolder>() {

    var datas = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.friendly_community_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imgProfile: ImageView = itemView.findViewById(R.id.sample0_img)
        private val txtName: TextView = itemView.findViewById(R.id.sample0_name)
        private val txtDesc: TextView = itemView.findViewById(R.id.sample0_description)

        fun bind(item: User) {
            Glide.with(itemView).load(item.image).into(imgProfile)
            txtName.text = item.name
            txtDesc.text = item.desc
        }
    }


}