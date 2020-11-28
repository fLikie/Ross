package com.example.rosseti.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rosseti.R

class EnumChoiceAdapter : RecyclerView.Adapter<EnumChoiceAdapter.EnumChoiceVH>() {

    private var data: List<Pair<String, Long>> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnumChoiceVH {
        return EnumChoiceVH(LayoutInflater.from(parent.context).inflate(R.layout.item_id_str_num, parent, false))
    }

    override fun onBindViewHolder(holder: EnumChoiceVH, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = data.size

    fun setData(data: List<Pair<String, Long>>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun getData() = data

    inner class EnumChoiceVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ordinalText: TextView = itemView.findViewById(R.id.item_id_str_num_id)
        private val stringText: TextView = itemView.findViewById(R.id.item_id_str_num_str)
        private val numText: TextView = itemView.findViewById(R.id.item_id_str_num_num)

        fun bind(position: Int) {
            ordinalText.text = (position + 1).toString()
            stringText.text = data[position].first
            numText.text = data[position].second.toString()
        }
    }
}