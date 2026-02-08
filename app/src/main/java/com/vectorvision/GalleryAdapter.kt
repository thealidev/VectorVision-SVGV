package com.vectorvision

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vectorvision.databinding.ItemMediaBinding

class GalleryAdapter(private val mediaList: List<MediaFile>) : 
    RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemMediaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMediaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mediaList[position]
        holder.binding.typeBadge.text = if (item.path.endsWith(".svgv")) "SVGV" else "SVGC"
        // Logic to call C++ thumbnail renderer [cite: 2026-02-07]
    }

    override fun getItemCount() = mediaList.size
    }
    
