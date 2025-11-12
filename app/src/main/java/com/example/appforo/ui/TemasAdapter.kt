package com.example.appforo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.appforo.data.Tema
import com.example.appforo.databinding.ItemTemaBinding

class TemasAdapter(private val onClick: (Long) -> Unit) :
    ListAdapter<Tema, TemasAdapter.VH>(DIFF) {

    object DIFF : DiffUtil.ItemCallback<Tema>() {
        override fun areItemsTheSame(oldItem: Tema, newItem: Tema) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Tema, newItem: Tema) = oldItem == newItem
    }

    inner class VH(private val binding: ItemTemaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Tema) {
            binding.tema = item
            binding.root.setOnClickListener { onClick(item.id) }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTemaBinding.inflate(inflater, parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
}

