package com.example.taskexecuterapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskexecuterapp.databinding.ItemTaskBinding

class ScreenAdapter(var list: MutableList<String>): RecyclerView.Adapter<ScreenAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.textView.text = item
    }

    override fun getItemCount(): Int = list.size

    fun updateList(newList: MutableList<String>){
        list = newList
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemTaskBinding): RecyclerView.ViewHolder(binding.root)
}

