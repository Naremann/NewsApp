package com.example.newsapp.database

import android.content.Context
import androidx.room.*
import com.example.newsapp.SourceTypeConverters
import com.example.newsapp.model.ArticlesItem
import com.example.newsapp.model.SourcesItem

@Database(entities = [SourcesItem :: class,ArticlesItem::class], version = 2, exportSchema = false)
@TypeConverters(SourceTypeConverters::class)
abstract class MyDatabase  : RoomDatabase() {
    abstract fun sourceDao() : SourcesDao

    companion object{
        const val DATABASE_NAME = "database"
        var database : MyDatabase? = null
        fun init(context:Context) {
            if(database == null){
                database = Room.databaseBuilder(
                    context,
                    MyDatabase::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration()
                    .build()
            }

        }
        fun getInstance() : MyDatabase{
            return database!!
        }

    }
}