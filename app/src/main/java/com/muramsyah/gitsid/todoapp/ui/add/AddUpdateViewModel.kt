package com.muramsyah.gitsid.todoapp.ui.add

import androidx.lifecycle.ViewModel
import com.muramsyah.gitsid.todoapp.data.source.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddUpdateViewModel @Inject constructor(private val repository: TodoRepository) : ViewModel() {

    fun updateTodo(id: Int, title: String, catatan: String, tanggal: String, status: String) =
        repository.updateTodoById(id, title, catatan, tanggal, status)

    fun deleteTodo(id: Int) = repository.deleteTodoById(id)

    fun addTodo(title: String, catatan: String, tanggal: String, status: String) =
        repository.addTodo(title, catatan, tanggal, status)
}