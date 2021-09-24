package com.muramsyah.gitsid.todoapp.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.muramsyah.gitsid.todoapp.data.source.remote.network.ApiSevice
import com.muramsyah.gitsid.todoapp.data.source.remote.response.TodoItem
import com.muramsyah.gitsid.todoapp.data.source.remote.response.TodoResponse
import com.muramsyah.gitsid.todoapp.data.source.remote.response.TodoUpdateResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiSevice: ApiSevice) {

    private val TAG = RemoteDataSource::class.java.simpleName

    fun getAllTodo(): LiveData<List<TodoItem>> {
        val result = MutableLiveData<List<TodoItem>>()

        apiSevice.getAllTodo("getAllTodo")
            .enqueue(object : Callback<TodoResponse>{
                override fun onResponse(
                    call: Call<TodoResponse>,
                    response: Response<TodoResponse>
                ) {
                    if (response.isSuccessful) {

                        val todoResponse = response.body()?.data
                        result.postValue(todoResponse)

                    } else {
                        Log.d(TAG, "onResponse: gagal mengambil data")
                    }
                }

                override fun onFailure(call: Call<TodoResponse>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })

        return result
    }

    fun updateTodoById(
        id: Int,
        title: String,
        catatan: String,
        tanggal: String,
        status: String
    ): LiveData<TodoUpdateResponse> {
        val result = MutableLiveData<TodoUpdateResponse>()

        apiSevice.updateTodo("updateTodoById", id, title, catatan, tanggal, status)
            .enqueue(object : Callback<TodoUpdateResponse> {
                override fun onResponse(
                    call: Call<TodoUpdateResponse>,
                    response: Response<TodoUpdateResponse>
                ) {
                    if (response.isSuccessful) {
                        result.postValue(response.body())
                    } else {
                        Log.d(TAG, "onResponse: gagal mengupdate data")
                    }
                }

                override fun onFailure(call: Call<TodoUpdateResponse>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })
        return result
    }

    fun deleteTodoById(id: Int): LiveData<TodoUpdateResponse> {
        val result = MutableLiveData<TodoUpdateResponse>()

        apiSevice.deleteTodoById("deleteTodoById", id)
            .enqueue(object : Callback<TodoUpdateResponse> {
                override fun onResponse(
                    call: Call<TodoUpdateResponse>,
                    response: Response<TodoUpdateResponse>
                ) {
                    if (response.isSuccessful) {
                        result.postValue(response.body())
                    } else {
                        Log.d(TAG, "onResponse: gagal mendelete data")
                    }
                }

                override fun onFailure(call: Call<TodoUpdateResponse>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })
        return result
    }

    fun addTodo(
        title: String,
        catatan: String,
        tanggal: String,
        status: String) : LiveData<TodoUpdateResponse> {
        val result = MutableLiveData<TodoUpdateResponse>()

        apiSevice.addTodo("insertTodo", title, catatan, tanggal, status)
            .enqueue(object : Callback<TodoUpdateResponse> {
                override fun onResponse(
                    call: Call<TodoUpdateResponse>,
                    response: Response<TodoUpdateResponse>
                ) {
                    if (response.isSuccessful) {
                        result.postValue(response.body())
                    } else {
                        Log.d(TAG, "onResponse: gagal menambahkan data")
                    }
                }

                override fun onFailure(call: Call<TodoUpdateResponse>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })
        return result
    }

}