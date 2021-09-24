package com.muramsyah.gitsid.todoapp.data.source

import androidx.lifecycle.LiveData
import com.muramsyah.gitsid.todoapp.data.source.remote.response.TodoItem
import com.muramsyah.gitsid.todoapp.data.source.remote.response.TodoUpdateResponse

interface TodoDataSource {

    fun getAllTodo(): LiveData<List<TodoItem>>

    fun updateTodoById(id: Int, title: String, catatan: String, tanggal: String, status: String): LiveData<TodoUpdateResponse>

    fun deleteTodoById(id: Int): LiveData<TodoUpdateResponse>

    fun addTodo(title: String, catatan: String, tanggal: String, status: String): LiveData<TodoUpdateResponse>
}