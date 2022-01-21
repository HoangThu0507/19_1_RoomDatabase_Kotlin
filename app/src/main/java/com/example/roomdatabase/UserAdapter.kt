package com.example.roomdatabase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(val listener: infor) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var mListUser: MutableList<User?>? = null
    lateinit var context: Context
    fun setData(list: MutableList<User?>?, context: Context) {
        mListUser = list
        this.context = context
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        var user = mListUser!![position] ?: return
        holder.tvUserName.setText(user.name)
        holder.tvUserAddress.setText(user.address)
        holder.imgDelete.setOnClickListener {
            UserDatabase.getInstance(context)!!.userDao()!!.deleteUser(mListUser!![position])
            mListUser!!.remove(mListUser!![position])
            notifyDataSetChanged()
        }
        holder.itemView.setOnClickListener{
            listener.update(mListUser!![position]!!)
        }
//        holder.itemUser.setOnClickListener {
////            var mainActivity = MainActivity()
//            MainActivity.set (user.name, user.address )
//            Toast.makeText(context, user.name, Toast.LENGTH_SHORT).show()
//        }

    }

    override fun getItemCount(): Int {
        return if (mListUser != null) {
            mListUser!!.size
        } else 0
    }

    inner class UserViewHolder(itemView: View,val listener: infor) : RecyclerView.ViewHolder(itemView) {
        val tvUserName: TextView
        val tvUserAddress: TextView
        val imgDelete: ImageView

        init {
            tvUserName = itemView.findViewById(R.id.tvUserName)
            tvUserAddress = itemView.findViewById(R.id.tvUserAddress)
            imgDelete = itemView.findViewById(R.id.imgDelete)
        }
    }
    interface infor{
        fun update(user: User)
    }
}
