package com.example.myapplication.data

import androidx.room.*
import com.example.myapplication.todolistitems.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Item)

    @Delete
    suspend fun deleteTodo(todo: Item)

    @Update
    suspend fun updateTodo(item: Item)

    @Query("SELECT * FROM todo_table WHERE id = :id")
    suspend fun getTodoById(id: Int): Item?

    @Query("SELECT * FROM todo_table")
    fun getTodos(): Flow<List<Item>>
}