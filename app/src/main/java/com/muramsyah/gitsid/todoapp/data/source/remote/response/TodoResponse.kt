package com.muramsyah.gitsid.todoapp.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TodoResponse(

    @field:SerializedName("data")
    val data: List<TodoItem>? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: Int? = null
) : Parcelable

@Parcelize
data class TodoItem(

    @field:SerializedName("catatan")
    val catatan: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("tanggal")
    val tanggal: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("status")
    val status: String? = null
) : Parcelable
