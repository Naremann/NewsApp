package com.example.newsapp

import androidx.room.TypeConverter
import com.example.newsapp.model.Source
import org.json.JSONObject

object SourceTypeConverters {
    @TypeConverter
    fun fromSource(source: Source): String {
        return JSONObject().apply {
            put("id", source.id)
            put("name", source.name)
        }.toString()
    }

    @TypeConverter
    fun toSource(source: String): Source {
        val json = JSONObject(source)
        return Source(json.get("id") as String?, json.getString("name"))
    }
}