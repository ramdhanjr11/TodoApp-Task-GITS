package com.muramsyah.gitsid.todoapp.data.source.remote.network

import com.muramsyah.gitsid.todoapp.data.source.remote.response.TodoResponse
import com.muramsyah.gitsid.todoapp.data.source.remote.response.TodoUpdateResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiSevice {

    @GET("todoAPI.php")
    fun getAllTodo(
        @Query("fun") function: String
    ) : Call<TodoResponse>

    @FormUrlEncoded
    @POST("todoAPI.php")
    fun updateTodo(
        @Query("fun") function: String,
        @Query("id") id: Int,
        @Field("title") title: String,
        @Field("catatan") catatan: String,
        @Field("tanggal") tanggal: String,
        @Field("status") status: String
    ) : Call<TodoUpdateResponse>

    @POST("todoAPI.php")
    fun deleteTodoById(
        @Query("fun") function: String,
        @Query("id") id: Int
    ) : Call<TodoUpdateResponse>

    @FormUrlEncoded
    @POST("todoAPI.php")
    fun addTodo(
        @Query("fun") function: String,
        @Field("title") title: String,
        @Field("catatan") catatan: String,
        @Field("tanggal") tanggal: String,
        @Field("status") status: String
    ) : Call<TodoUpdateResponse>
}