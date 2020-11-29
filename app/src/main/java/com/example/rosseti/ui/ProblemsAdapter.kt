package com.example.rosseti.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rosseti.R

class ProblemsAdapter : RecyclerView.Adapter<ProblemsAdapter.ProblemsVH>() {
    val data = listOf(1,2,3,4,5)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemsVH {
        return ProblemsVH(LayoutInflater.from(parent.context).inflate(R.layout.item_card_idea, parent, false))
    }

    override fun onBindViewHolder(holder: ProblemsVH, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = data.size

    inner class ProblemsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(position: Int) {

        }
    }
}