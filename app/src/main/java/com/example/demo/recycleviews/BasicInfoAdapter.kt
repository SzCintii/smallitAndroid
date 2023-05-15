package com.example.demo.recycleviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class BasicInfoAdapter (val clickListener: BasicInfoClickListener):  ListAdapter<Basic, BasicInfoAdapter.ViewHolder>(BasicInfoDiffCallback()) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        return ViewHolder.from(parent)
    }
    class ViewHolder private constructor(val binding:
                                         BasicInfoListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(
            basic: Basic,
            clickListener: BasicInfoClickListener
        ) {
            binding.basic = basic
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    BasicInfoListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}
class BasicInfoDiffCallback : DiffUtil.ItemCallback<Basic>() {
    override fun areItemsTheSame(oldItem: Basic, newItem: Basic): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: Basic, newItem: Basic): Boolean
    {
        return oldItem == newItem
    }
}
class BasicInfoClickListener(val clickListener: (basic: Basic) -> Unit) {
    fun onClick(basic: Basic) = clickListener(basic)
}
