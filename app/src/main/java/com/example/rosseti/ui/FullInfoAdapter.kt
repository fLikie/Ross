package com.example.rosseti.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rosseti.R
import kotlin.random.Random

class FullInfoAdapter : RecyclerView.Adapter<FullInfoAdapter.VH>() {
    val nameList = listOf("Николай", "Сергей", "Владимир", "Иван", "Петр")
    val lastNameList = listOf("Петров", "Иванов", "Кузнецов", "Егоров", "Максимов")
    val commentList = listOf("Потрясающе!", "Осуждаю.", "Круто! Надо продолжать работать.", "Здорово!", "Что это такое?")

    var data = mutableListOf<Pair<String, String>>().apply {
        val times = Random.nextInt(1,10)
        repeat (times) {
            add("${nameList.random()} ${lastNameList.random()}" to commentList.random())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = data.size

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.name_and_last_name)
        val commentText: TextView = itemView.findViewById(R.id.comment_text)

        fun bind(position: Int) {
            nameText.text = data[position].first
            commentText.text = data[position].second
        }
    }
}