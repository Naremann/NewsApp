package com.example.newsapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
//GET responses(model class)
//1- new -> generate from json
//2-copy example response from newsApi.org and past in the screen
data class SourcesRespones(

	@field:SerializedName("sources")
    var sources: List<SourcesItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("code")
	val code: List<SourcesItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)
@Entity
data class SourcesItem(

	@ColumnInfo
	@field:SerializedName("country")
	val country: String? = null,

	@ColumnInfo
	@field:SerializedName("name")
	val name: String? = null,

	@ColumnInfo
	@field:SerializedName("description")
	val description: String? = null,

	@ColumnInfo
	@field:SerializedName("language")
	val language: String? = null,

	@PrimaryKey
	@ColumnInfo
	@field:SerializedName("id")
	val id: String,

	@ColumnInfo
	@field:SerializedName("category")
	val category: String? = null,

	@ColumnInfo
	@field:SerializedName("url")
	val url: String? = null
)
