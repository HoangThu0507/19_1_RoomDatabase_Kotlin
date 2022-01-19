package com.example.roomdatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var mListUser: List<User>? = null
    fun setData(list: List<User>?) {
        mListUser = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = mListUser!![position] ?: return
        holder.tvUserName.setText(user.name)
        holder.tvUserAddress.setText(user.address)
    }

    override fun getItemCount(): Int {
        return if (mListUser != null) {
            mListUser!!.size
        } else 0
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvUserName: TextView
        val tvUserAddress: TextView

        init {
            tvUserName = itemView.findViewById(R.id.tvUserName)
            tvUserAddress = itemView.findViewById(R.id.tvUserAddress)
        }
    }
}
