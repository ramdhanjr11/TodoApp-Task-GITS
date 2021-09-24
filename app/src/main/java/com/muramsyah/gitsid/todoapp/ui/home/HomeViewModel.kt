package com.muramsyah.gitsid.todoapp.ui.home

import androidx.lifecycle.ViewModel
import com.muramsyah.gitsid.todoapp.data.source.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(repository: TodoRepository) : ViewModel() {

    val allTodo = repository.getAllTodo()

}