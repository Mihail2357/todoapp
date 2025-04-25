package com.example.myapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.myapplication.todolistitems.Item
import androidx.room.RoomDatabase

@Database(
    entities = [Item::class],
    version = 1,
)
abstract class TodoDatabase: RoomDatabase() {

    abstract val dao: TodoDao

    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getDatabase(context: Context): TodoDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) { // Ensure only one instance is created
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todo_database" // Database name
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}