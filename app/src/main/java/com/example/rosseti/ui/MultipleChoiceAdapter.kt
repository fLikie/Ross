package com.example.rosseti.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rosseti.R

class MultipleChoiceAdapter(private val onAnyItemSelected: (Boolean) -> Unit) : RecyclerView.Adapter<MultipleChoiceAdapter.MultipleChoiceViewHolder>() {
    private var data = mutableListOf<Triple<Int, String, Boolean>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultipleChoiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_multiple_choice, parent, false)
        return MultipleChoiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: MultipleChoiceViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = data.size

    fun setData(data: MutableList<Triple<Int, String, Boolean>>) {
        this.data = data
        notifyDataSetChanged()
    }

    @JvmName("setDataUnmutable")
    fun setData(data: List<Triple<Int, String, Boolean>>) {
        setData(ArrayList(data))
    }

    fun getData() = data

    inner class MultipleChoiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textView: TextView = itemView.findViewById(R.id.choice_text)

        init {
            textView.setOnClickListener {
                val isActive = !textView.isActivated
                val info = data.removeAt(adapterPosition).copy(third = isActive)
                data.add(adapterPosition, info)
                onAnyItemSelected(data.any { it.third })
                notifyDataSetChanged()
            }
        }

        fun bind(index: Int) {
            textView.text = data[index].second
            textView.isActivated = data[index].third
        }
    }
}