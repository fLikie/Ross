package com.example.rosseti.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.rosseti.R
import com.example.rosseti.domain.entities.Topic
import kotlinx.android.synthetic.main.item_tag.view.*

class ChooseTagsAdapter : RecyclerView.Adapter<ChooseTagsAdapter.ViewHolder>() {

    private val tags: MutableList<Topic> = mutableListOf()

    fun setTags(tags: List<Topic>) {
        this.tags.clear()
        this.tags.addAll(tags)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_tag, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tags[position])
    }

    override fun getItemCount(): Int {
        return tags.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var selected = false

        init {
            itemView.tag_text.setOnClickListener {
                if (selected) {
                    it.tag_text.setTextColor(ContextCompat.getColor(it.context, R.color.blue))
                    it.tag_text.background = ContextCompat.getDrawable(it.context, R.drawable.tag_border)
                } else {
                    it.tag_text.setTextColor(ContextCompat.getColor(it.context, R.color.white))
                    it.tag_text.background = ContextCompat.getDrawable(it.context, R.drawable.tag_border_selected)
                }
                selected = !selected
            }
        }

        fun bind(topic: Topic) {
            itemView.tag_text.text = topic.name
        }
    }
}