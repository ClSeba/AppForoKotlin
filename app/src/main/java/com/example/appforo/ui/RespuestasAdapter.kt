package com.example.appforo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.appforo.data.Respuesta
import com.example.appforo.databinding.ItemRespuestaBinding

class RespuestasAdapter : ListAdapter<Respuesta, RespuestasAdapter.VH>(DIFF) {
    object DIFF : DiffUtil.ItemCallback<Respuesta>() {
        override fun areItemsTheSame(oldItem: Respuesta, newItem: Respuesta) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Respuesta, newItem: Respuesta) = oldItem == newItem
    }

    inner class VH(private val binding: ItemRespuestaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Respuesta) {
            binding.respuesta = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRespuestaBinding.inflate(inflater, parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
}

