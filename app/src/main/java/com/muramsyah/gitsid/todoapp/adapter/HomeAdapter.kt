package com.muramsyah.gitsid.todoapp.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muramsyah.gitsid.todoapp.R
import com.muramsyah.gitsid.todoapp.data.source.remote.response.TodoItem
import com.muramsyah.gitsid.todoapp.databinding.ItemLayoutTodoBinding

@SuppressLint("ResourceAsColor")
class HomeAdapter(val data: List<TodoItem>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var onItemClicked : ((TodoItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemLayoutTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: ItemLayoutTodoBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(todo: TodoItem) {
            with(binding) {
                tvTitle.text = todo.title
                tvCatatan.text = todo.catatan
                tvTanggal.text = todo.tanggal
            }

            itemView.setOnClickListener {
                onItemClicked?.invoke(todo)
            }

            val colors = arrayOf(
                itemView.resources.getColor(R.color.blue_secondary),
                itemView.resources.getColor(R.color.blue_primary),
                itemView.resources.getColor(R.color.pink),
                itemView.resources.getColor(R.color.light_pink),
                itemView.resources.getColor(R.color.orange)
            )

            binding.container.setBackgroundColor(colors[(0..4).random()])
        }
    }

}