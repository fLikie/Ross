package com.example.rosseti.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.rosseti.R
import kotlin.random.Random

class ProblemsAdapter : RecyclerView.Adapter<ProblemsAdapter.ProblemsVH>() {
    val data = if (Random.nextBoolean()) listOf(1,2,3,4,5) else listOf(1,2)

    val nameList = listOf("Николай", "Сергей", "Владимир", "Иван", "Петр")
    val lastNameList = listOf("Петров", "Иванов", "Кузнецов", "Егоров", "Максимов")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemsVH {
        return ProblemsVH(LayoutInflater.from(parent.context).inflate(R.layout.item_card_idea, parent, false))
    }

    override fun onBindViewHolder(holder: ProblemsVH, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = data.size

    inner class ProblemsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ratingTextView: TextView = itemView.findViewById(R.id.rating_score)
        val nameTextView: TextView = itemView.findViewById(R.id.name)
        val dateTextView: TextView = itemView.findViewById(R.id.publication_date)
        val viewsCountTextView: TextView = itemView.findViewById(R.id.views_counter)
        val arrowUpButton: ImageView = itemView.findViewById(R.id.rating_up)
        val arrowDownButton: ImageView = itemView.findViewById(R.id.rating_down)

        init {
            arrowUpButton.setOnClickListener {
                Toast.makeText(itemView.context, "Вы проголосовали за", Toast.LENGTH_SHORT).show()
            }
            arrowDownButton.setOnClickListener {
                Toast.makeText(itemView.context, "Вы проголосовали против", Toast.LENGTH_SHORT).show()
            }
        }

        fun bind(position: Int) {
            val randomRating = Random.nextInt(0,99)
            ratingTextView.text = randomRating.toString()
            nameTextView.text = "${lastNameList.random()} ${nameList.random()}"
            val daysText = if (position == 0) "день" else if (position in 1..4) "дня" else "дней"
            dateTextView.text = "${(position + 1)} $daysText назад"
            viewsCountTextView.text = (randomRating + 13).toString()
        }
    }
}