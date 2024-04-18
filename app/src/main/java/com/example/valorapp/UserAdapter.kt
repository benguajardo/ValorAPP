package com.example.valorapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.valorapp.databinding.ItemUserBinding

class UserAdapter(private val users: List<User>, private val listener:OnClickListener) : RecyclerView.Adapter<UserAdapter.ViewHolder>(){

    private lateinit var context:Context

    // SIRVE PARA INFLAR VISTA
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    // RELLENA VISTA
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users.get(position)
        with(holder){
            setListener(user,position)
            binding.tvOrder.text = user.id.toString()
            binding.tvName.text = user.name
            Glide.with(context)
                .load(user.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.imgPhoto)
        }
    }

    // INDICA CUANTOS ELEMENTOS HAY EN ADAPTER
    override fun getItemCount(): Int = users.size

    inner class ViewHolder(view:View) : RecyclerView.ViewHolder(view){
        val binding = ItemUserBinding.bind(view)

        fun setListener (user: User, position : Int){
            binding.root.setOnClickListener{ listener.onClick(user,position) }
        }
    }

}