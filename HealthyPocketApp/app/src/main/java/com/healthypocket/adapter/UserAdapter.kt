package com.healthypocket.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.healthypocket.R
import com.healthypocket.model.Medicamento
import com.healthypocket.model.User

class UserAdapter(private var userList: List<User>) :
    RecyclerView.Adapter<UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UserViewHolder(
            layoutInflater.inflate(
                R.layout.item_medicamento,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = userList[position]

        holder.render(item)

        holder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?){

            }
        })
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(listuser: List<User>){
        userList = listuser
    }

    fun getuserID(position: Int): String? = userList[position]._id

    fun getNombreUser(position: Int):String? = userList[position].name
    fun getPassUser(position: Int):String? = userList[position].password

}