package com.muramsyah.gitsid.todoapp.data.source

import androidx.lifecycle.LiveData
import com.muramsyah.gitsid.todoapp.data.source.remote.RemoteDataSource
import com.muramsyah.gitsid.todoapp.data.source.remote.response.TodoItem
import com.muramsyah.gitsid.todoapp.data.source.remote.response.TodoUpdateResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : TodoDataSource{

    override fun getAllTodo(): LiveData<List<TodoItem>> {
        return remoteDataSource.getAllTodo()
    }

    override fun updateTodoById(
        id: Int,
        title: String,
        catatan: String,
        tanggal: String,
        status: String
    ): LiveData<TodoUpdateResponse> {
        return remoteDataSource.updateTodoById(id, title, catatan, tanggal, status)
    }

    override fun deleteTodoById(id: Int): LiveData<TodoUpdateResponse> {
        return remoteDataSource.deleteTodoById(id)
    }

    override fun addTodo(
        title: String,
        catatan: String,
        tanggal: String,
        status: String
    ): LiveData<TodoUpdateResponse> {
        return remoteDataSource.addTodo(title, catatan, tanggal, status)
    }

}